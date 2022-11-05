package com.org.citius.pms.user.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.citius.pms.user.controller.dto.Login;
import com.org.citius.pms.user.controller.dto.PasswordResetType;
import com.org.citius.pms.user.controller.dto.ResetUserPassword;
import com.org.citius.pms.user.controller.dto.UserInformation;
import com.org.citius.pms.user.repository.UserRepository;
import com.org.citius.pms.user.service.dao.User;
import com.org.citius.pms.user.service.dao.UserStatus;
import com.org.citius.pms.user.util.UserUtility;
import com.org.citius.pms.user.util.constants.AppConstants;
import com.org.citius.pms.user.util.exception.FirstTimeLoginException;
import com.org.citius.pms.user.util.exception.InvalidUserPasswordException;
import com.org.citius.pms.user.util.exception.UserAccountDeletedException;
import com.org.citius.pms.user.util.exception.UserAccountDisabledException;
import com.org.citius.pms.user.util.exception.UserAccountLockException;
import com.org.citius.pms.user.util.exception.UserAccountPasswordExpiredException;
import com.org.citius.pms.user.util.exception.UserNotFoundException;
import com.org.citius.pms.user.util.exception.UserStatusNotFoundException;
import com.org.citius.pms.user.util.exception.handler.response.ApplicationResponse;
import com.org.citius.pms.user.util.mapper.UserToUserInformationDtoMapper;

import freemarker.template.TemplateException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserToUserInformationDtoMapper userToUserInformationDtoMapper;

	@Autowired
	private UserUtility userUtility;

	@Autowired
	private UserStatusService userStatusService;

	@Transactional(readOnly = true)
	private User queryUserName(String username) throws IllegalArgumentException, UserNotFoundException {
		if (Objects.isNull(username))
			throw new IllegalArgumentException("Username cannot be null / empty...");
		return Optional.ofNullable(this.userRepository.findByEmailId(username))
				.orElseThrow(() -> new UserNotFoundException(AppConstants.ERROR_MESSAGE_USER_ACCOUNT_NOT_FOUND));
	}

	public UserInformation login(Login loginUser) throws IllegalArgumentException, UserNotFoundException,
			InvalidUserPasswordException, UserAccountLockException, FirstTimeLoginException,
			UserStatusNotFoundException, UserAccountDisabledException, UserAccountDeletedException {
		UserInformation userInformation = null;
		User user = queryUserName(loginUser.getUserName());
		if (this.userUtility.validUserAccount(user)) {
			if (!this.userUtility.validatePasswordExpiry(user)) {
				UserStatus userStatus = this.userStatusService.queryByStatusCodeName("Password_Expired");
				user.setUserStatus(userStatus);
				this.userRepository.save(user);
				throw new UserAccountPasswordExpiredException(AppConstants.ERROR_MESSAGE_ACCOUNT_PASSWORD_EXPIRED);
			} else if (!this.userUtility.validateUserPassword(loginUser, user)) {
				String messsage = "";
				if (user.getInvalidRetryCount() == 0) {
					messsage = "Bad request - 2 login attempt is remaining";
				} else if (user.getInvalidRetryCount() == 1) {
					messsage = "Bad request - 1 login attempt is remaining";
				} else {
					messsage = "Bad request - Your account has been locked. Please contact the hospital administrator or call helpdesk on 123456 for more information.";
					UserStatus userStatus = this.userStatusService.queryByStatusCodeName("Locked");
					user.setUserStatus(userStatus);
				}
				user.setInvalidRetryCount((user.getInvalidRetryCount() + 1));
				this.userRepository.save(user);
				throw new InvalidUserPasswordException(messsage);
			} else {
				user.setInvalidRetryCount(0);
				user.setIsFirstTimeLogin(false);
				this.userRepository.save(user);
				userInformation = this.userToUserInformationDtoMapper.convert(user); // dao to dto mapping
			}
		}
		return userInformation;
	}

	public ApplicationResponse resetPassword(ResetUserPassword resetUserPassword)
			throws IllegalArgumentException, UserNotFoundException, UserAccountDisabledException,
			UserAccountDeletedException, InvalidUserPasswordException, UserStatusNotFoundException, RuntimeException,
			MessagingException, IOException, TemplateException {

		if (Objects.nonNull(resetUserPassword) && Objects.nonNull(resetUserPassword.getPwdResetType())) {
			User user = queryUserName(resetUserPassword.getUserName());
			if (this.userUtility.validateUserAccountForDeletedorDisabled(user)) {
				if (PasswordResetType.RESET.getValue().equals(resetUserPassword.getPwdResetType())) {

					StringBuilder defaultPassword = new StringBuilder();
					defaultPassword.append(
							Objects.nonNull(user.getFirstName()) ? user.getFirstName().toUpperCase().charAt(0) : "D");
					defaultPassword.append(
							Objects.nonNull(user.getFirstName()) ? user.getFirstName().toLowerCase().subSequence(1, 4)
									: "fault");
					defaultPassword.append(Objects.nonNull(user.getDob()) ? user.getDob().getYear() : "2021");
					String encryptedNewPassword = this.userUtility
							.get_SHA_512_SecurePassword(defaultPassword.toString());
					user.setPasswrd(encryptedNewPassword);
					user.setPasswrdExpiryDateTime(LocalDateTime.now().atOffset(ZoneOffset.UTC)
							.plusHours(AppConstants.ADD_PASSWORD_EXPIRY_2_HOURS).toLocalDateTime());
					user.setIsFirstTimeLogin(true);
					user.setModifiedBy(user.getId());
					user.setModifiedDateTime(LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime());
					this.userRepository.save(user);

					// send email
					Map<String, Object> templateModel = new HashMap<>();
					templateModel.put("name", ((Objects.nonNull(user.getFirstName()) ? user.getFirstName() : " ") + " "
							+ (Objects.nonNull(user.getLastName()) ? user.getLastName() : " ")));
					emailService.sendMessageUsingFreemarkerTemplateForResetpassword(
							(Objects.nonNull(user.getEmailId()) ? user.getEmailId() : " "),
							AppConstants.RESET_PASSWORD_FOR_PMS_PORTAL, templateModel);

					return this.userUtility.successApplicationResponse(AppConstants.USER_PASSWORD_RESET_SUCCESS);

				} else {
					if (!(this.userUtility.validateOldPassword(resetUserPassword.getOldPasswrd(), user.getPasswrd()))) {
						throw new InvalidUserPasswordException(AppConstants.ERROR_MESSAGE_IVALID_OLD_PASSWORD);
					}
					if (!this.userUtility.validatePasswordExpiry(user)) {
						UserStatus userStatus = this.userStatusService.queryByStatusCodeName("Password_Expired");
						user.setUserStatus(userStatus);
						this.userRepository.save(user);
						throw new UserAccountPasswordExpiredException(
								AppConstants.ERROR_MESSAGE_ACCOUNT_PASSWORD_EXPIRED);
					}
					if (this.userUtility.validateOldAndNewPassword(resetUserPassword.getOldPasswrd(),
							resetUserPassword.getNewPasswrd())) {
						throw new InvalidUserPasswordException(AppConstants.ERROR_MESSAGE_SAME_OLD_AND_NEW_PASSWORD);
					}
					if (!(this.userUtility.validateNewAndConfirmPassword(resetUserPassword.getNewPasswrd(),
							resetUserPassword.getConfirmPasswrd()))) {
						throw new InvalidUserPasswordException(
								AppConstants.ERROR_MESSAGE_INVALID_NEW_AND_CONFIRM_PASSWORD);
					}
					String encryptedNewPassword = this.userUtility
							.get_SHA_512_SecurePassword(resetUserPassword.getNewPasswrd());
					user.setPasswrd(encryptedNewPassword);
					user.setPasswrdExpiryDateTime(LocalDateTime.now().atOffset(ZoneOffset.UTC)
							.plusDays(AppConstants.ADD_PASSWORD_EXPIRY_45_DAYS).toLocalDateTime());
					UserStatus userStatus = this.userStatusService.queryByStatusCodeName("Active");
					user.setUserStatus(userStatus);
					user.setInvalidRetryCount(0);
					user.setIsFirstTimeLogin(false);
					user.setModifiedBy(user.getId());
					user.setModifiedDateTime(LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime());
					this.userRepository.save(user);
					return this.userUtility.successApplicationResponse(AppConstants.USER_PASSWORD_CHANGE_SUCCSESS);
				}
			}
		}
		throw new RuntimeException(AppConstants.ERROR_MESSAGE_UNABLE_TO_PROCESS_REQUEST);
	}

}

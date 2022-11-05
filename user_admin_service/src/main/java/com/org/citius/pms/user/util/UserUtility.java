package com.org.citius.pms.user.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.org.citius.pms.user.controller.dto.Login;
import com.org.citius.pms.user.service.dao.User;
import com.org.citius.pms.user.util.constants.AppConstants;
import com.org.citius.pms.user.util.exception.FirstTimeLoginException;
import com.org.citius.pms.user.util.exception.UserAccountDeletedException;
import com.org.citius.pms.user.util.exception.UserAccountDisabledException;
import com.org.citius.pms.user.util.exception.UserAccountLockException;
import com.org.citius.pms.user.util.exception.UserAccountPasswordExpiredException;
import com.org.citius.pms.user.util.exception.handler.response.ApplicationResponse;

@Component
public class UserUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserUtility.class);

	private final String SALT = "CITIUS_512_ENCRYPT";

	public String get_SHA_512_SecurePassword(String passwordToHash) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(this.SALT.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Exception SHA512 ::" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public boolean validatePasswordExpiry(User user) {
		Boolean result = false;
		if (Objects.nonNull(user) && Objects.nonNull(user.getPasswrdExpiryDateTime())) {
			result = LocalDateTime.now().atOffset(ZoneOffset.UTC)
					.isAfter(user.getPasswrdExpiryDateTime().atOffset(ZoneOffset.UTC)) ? false : true;
		}
		return result;
	}

	public boolean validateUserPassword(Login loginUser, User user) {
		Boolean result = false;
		String encryptedLoginPassword = "";
		if (Objects.nonNull(loginUser.getPasswrd())) {
			encryptedLoginPassword = this.get_SHA_512_SecurePassword(loginUser.getPasswrd());
		}
		result = validateUserEncryptedPassword(encryptedLoginPassword,
				Objects.nonNull(user.getPasswrd()) ? user.getPasswrd() : "");
		return result;
	}

	public boolean validateUserEncryptedPassword(String encryptedEnteredPassword, String encryptedDataBasePassword) {
		Boolean result = false;
		if (Objects.nonNull(encryptedDataBasePassword) && Objects.nonNull(encryptedEnteredPassword)) {
			if (encryptedEnteredPassword.equals(encryptedDataBasePassword))
				result = true;
		}
		return result;
	}

	public boolean validUserAccount(User user) throws UserAccountLockException, FirstTimeLoginException,
			UserAccountDisabledException, UserAccountDeletedException {
		Boolean result = true;
		if (Objects.nonNull(user) && Objects.nonNull(user.getIsFirstTimeLogin())
				&& user.getIsFirstTimeLogin() == true) {
			throw new FirstTimeLoginException(AppConstants.ERROR_MESSAGE_FIRST_TIME_LOGIN);
		}
		if (Objects.nonNull(user) && Objects.nonNull(user.getUserStatus())
				&& Objects.nonNull(user.getUserStatus().getId()) && !(user.getUserStatus().getId() == 1)) {
			if (user.getUserStatus().getId() == 2)
				throw new UserAccountLockException(AppConstants.ERROR_MESSAGE_ACCOUNT_LOCK);
			else if (user.getUserStatus().getId() == 3) {
				throw new UserAccountDisabledException(AppConstants.ERROR_MESSAGE_ACCOUNT_DISABLE);
			} else if (user.getUserStatus().getId() == 4) {
				throw new UserAccountDeletedException(AppConstants.ERROR_MESSAGE_ACCOUNT_DELETED);
			} else if (user.getUserStatus().getId() == 5) {
				throw new UserAccountPasswordExpiredException(AppConstants.ERROR_MESSAGE_ACCOUNT_PASSWORD_EXPIRED);
			}
		}
		return result;
	}

	public boolean validateUserAccountForDeletedorDisabled(User user)
			throws UserAccountDisabledException, UserAccountDeletedException {
		Boolean result = true;
		if (Objects.nonNull(user) && Objects.nonNull(user.getUserStatus())
				&& Objects.nonNull(user.getUserStatus().getId())) {
			if (user.getUserStatus().getId() == 3) {
				throw new UserAccountDisabledException(AppConstants.ERROR_MESSAGE_ACCOUNT_DISABLE);
			} else if (user.getUserStatus().getId() == 4) {
				throw new UserAccountDeletedException(AppConstants.ERROR_MESSAGE_ACCOUNT_DELETED);
			}
		}
		return result;
	}

	public ApplicationResponse successApplicationResponse() {
		final ApplicationResponse response = new ApplicationResponse(LocalDateTime.now(ZoneOffset.UTC),
				AppConstants.SUCCESS, AppConstants.SUCCESS, HttpStatus.OK.value(), "");
		return response;
	}

	public boolean validateOldPassword(String oldPasswrd, String passwrd) {
		Boolean result = false;
		String encryptedEnteredPassword = "";
		if (Objects.nonNull(oldPasswrd)) {
			encryptedEnteredPassword = this.get_SHA_512_SecurePassword(oldPasswrd);
		}
		result = validateUserEncryptedPassword(encryptedEnteredPassword, Objects.nonNull(passwrd) ? passwrd : "");
		return result;
	}

	public boolean validateOldAndNewPassword(String oldPasswrd, String newPasswrd) {
		Boolean result = false;
		String encryptedEnteredOldPassword = "";
		String encryptedEnteredNewPassword = "";
		if (Objects.nonNull(oldPasswrd)) {
			encryptedEnteredOldPassword = this.get_SHA_512_SecurePassword(oldPasswrd);
		}
		if (Objects.nonNull(newPasswrd)) {
			encryptedEnteredNewPassword = this.get_SHA_512_SecurePassword(newPasswrd);
		}
		result = validateUserEncryptedPassword(encryptedEnteredOldPassword, encryptedEnteredNewPassword);
		return result;
	}

	public boolean validateNewAndConfirmPassword(String newPasswrd, String confirmPasswrd) {
		Boolean result = false;
		if (Objects.nonNull(newPasswrd) && Objects.nonNull(confirmPasswrd)) {
			if (newPasswrd.equals(confirmPasswrd))
				return true;
		}
		return result;
	}

	public ApplicationResponse successApplicationResponse(String message) {
		final ApplicationResponse response = new ApplicationResponse(LocalDateTime.now(ZoneOffset.UTC), message,
				AppConstants.SUCCESS, HttpStatus.OK.value(), "");
		return response;
	}

}

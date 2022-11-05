package com.org.citius.pms.user.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.citius.pms.user.controller.dto.Login;
import com.org.citius.pms.user.controller.dto.RegisterUserDetails;
import com.org.citius.pms.user.controller.dto.ResetUserPassword;
import com.org.citius.pms.user.controller.dto.UserInformation;
import com.org.citius.pms.user.service.UserService;
import com.org.citius.pms.user.util.exception.FirstTimeLoginException;
import com.org.citius.pms.user.util.exception.InvalidUserPasswordException;
import com.org.citius.pms.user.util.exception.UserAccountDeletedException;
import com.org.citius.pms.user.util.exception.UserAccountDisabledException;
import com.org.citius.pms.user.util.exception.UserAccountLockException;
import com.org.citius.pms.user.util.exception.UserNotFoundException;
import com.org.citius.pms.user.util.exception.UserStatusNotFoundException;
import com.org.citius.pms.user.util.exception.handler.response.ApplicationResponse;

import freemarker.template.TemplateException;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserInformation login(@RequestBody @Valid Login user) throws IllegalArgumentException, UserNotFoundException,
			InvalidUserPasswordException, UserAccountLockException, FirstTimeLoginException,
			UserStatusNotFoundException, UserAccountDisabledException, UserAccountDeletedException, Exception {
		
		LOGGER.info("UserController ::  login user execution started");
		try {
			return this.userService.login(user);
		} finally {
			LOGGER.info("UserController ::  login user execution completed");
		}
	}

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApplicationResponse register(@RequestBody RegisterUserDetails registerUser) {
		LOGGER.info("UserController ::  register user execution started");
		try {
			return null;
		} finally {
			LOGGER.info("UserController ::  register user execution completed");
		}
	}

	@PostMapping(path = "/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApplicationResponse reset(@RequestBody @Valid ResetUserPassword resetUserPassword)
			throws IllegalArgumentException, UserNotFoundException, UserAccountDisabledException,
			UserAccountDeletedException, InvalidUserPasswordException, UserStatusNotFoundException, RuntimeException,
			MessagingException, IOException, TemplateException {
		LOGGER.info("UserController ::  reset user password execution started");
		try {
			return this.userService.resetPassword(resetUserPassword);
		} finally {
			LOGGER.info("UserController ::  reset user password execution completed");
		}
	}
}

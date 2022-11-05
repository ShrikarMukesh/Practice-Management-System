package com.org.citius.pms.user.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserUtilityTest {

	@InjectMocks
	private UserUtility userUtility;

	@Test
	public void test_get_SHA_512_SecurePassword() {
		String passwordToHash = "Admin1234";
		String encryptedTestHashedPassword = "6e0389b20ee3cacece92dd102dc9c0f75585f50ba415ff77a929ca130372e297d32a30e42d3a23e9346474731112818e8fb0c4df7a853ad7c838efeb63130694";
		String encryptedHashedPassword = "";
		encryptedHashedPassword = this.userUtility.get_SHA_512_SecurePassword(passwordToHash);
		assertTrue(encryptedHashedPassword.equals(encryptedTestHashedPassword));
	}

}

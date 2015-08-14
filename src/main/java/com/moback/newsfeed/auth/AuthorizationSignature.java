package com.moback.newsfeed.auth;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Mac;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationSignature {

	private String keyPhrase = "Funny Key Phrase";
	private String TOKEN_VALUE_SEPARATOR = "|";
	private String TOKEN_SIGNATURE_SEPARATOR = ".";

	@Autowired
	private Mac mac;

	@Autowired
	private MessageDigest sha1;

	private String calculateSignature(String text) {
		byte[] hash = this.mac.doFinal((text + keyPhrase).getBytes());
		return Hex.encodeHexString(hash);
	}

	public String createAuthTokenString(String userId, long expirationTime) {
		/*
		 * StringBuilder : provides an API compatible with StringBuffer, but
		 * with no guarantee of synchronization. StringBuffer : thread-safe,
		 * mutable sequence of characters. A string buffer is like a String, but
		 * can be modified
		 */
		StringBuilder text = getValueStringBuilder(userId, String.valueOf(expirationTime));
		String signature = calculateSignature(text.toString());
		text.append(TOKEN_SIGNATURE_SEPARATOR).append(signature);
		return Base64.getEncoder().encodeToString(text.toString().getBytes());
	}

	private StringBuilder getValueStringBuilder(String userId, String expirationTime) {
		return new StringBuilder(userId).append(TOKEN_VALUE_SEPARATOR).append(expirationTime).append(TOKEN_VALUE_SEPARATOR);
	}

	private String[] splitValueString(String valueStr) {
		return valueStr.split("\\" + TOKEN_VALUE_SEPARATOR);
	}

	public void validateAuthToken(String authTokenString)
			throws TokenValidationException {
		if (authTokenString == null) {
			throw new TokenValidationException();
		}
		AuthToken authToken = this.parseAuthTokenString(authTokenString);
		String calculatedSignature = calculateSignature(authToken.getValueString());
		if (!calculatedSignature.equals(authToken.getSignature())) {
			throw new TokenValidationException();
		}

		if (authToken.getExpirationTime() < System.currentTimeMillis()) {
			throw new TokenValidationException();
		}
	}

	public AuthToken parseAuthTokenString(String authTokenString) throws TokenValidationException {
		if (authTokenString == null) {
			throw new IllegalArgumentException();
		}
		String decoded = new String(Base64.getDecoder().decode(authTokenString));
		int separatorIndex = decoded.lastIndexOf(TOKEN_SIGNATURE_SEPARATOR);
		String signature = decoded.substring(separatorIndex + 1);
		String valueStr = decoded.substring(0, separatorIndex);
		String[] parts = splitValueString(valueStr);
		if (parts.length < 2) {
			throw new TokenValidationException();
		}
		AuthToken authToken = new AuthToken();
		authToken.setUserId(parts[0]);
		authToken.setExpirationTime(Long.valueOf(parts[1]));
		authToken.setSignature(signature);
		authToken.setValueString(valueStr);
		return authToken;
	}
}

package com.project.quickbook.oauth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public interface OAuthService {
	
	String requestTokenForAuthorization(HttpServletRequest param_objRequest);
	void requestTokenReady(HttpServletRequest param_objRequest, String param_objRealmId, String param_objOAuthVerifier, String param_objOAuthToken);
	void requestReconnectTokenForAuthorization();
	
}

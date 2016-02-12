package com.project.quickbook.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.quickbook.oauth.service.OAuthService;
import com.project.quickbook.ws.CustomerController;

@RestController
public class OAuthController {
	
	private Logger logger = LoggerFactory.getLogger("OAuthController.class");
	
	@Autowired
	OAuthService g_objOAuthServices;

	@RequestMapping(value="/request_reconnect_token", method=RequestMethod.GET)
	public void requestReconnectTokenForAuthorization(){
		
		g_objOAuthServices.requestReconnectTokenForAuthorization();
	}
	
	@RequestMapping(value="/request_token", method=RequestMethod.GET)
	public void requestTokenForAuthorization(final HttpServletResponse param_objResponse, final HttpServletRequest param_objRequest,
            @RequestParam(value = "appCompanyId", required = true) String param_objAppCompanyId) throws IOException{
		
		String v_objResponseRedirectUrlForAuthorization;
		
		v_objResponseRedirectUrlForAuthorization = g_objOAuthServices.requestTokenForAuthorization(param_objRequest);
	
		param_objResponse.sendRedirect(v_objResponseRedirectUrlForAuthorization);
	}
	
	@RequestMapping(value="/request_token_ready", method=RequestMethod.GET)
	public void requestTokenReady(HttpServletRequest param_objRequest,
			@RequestParam(value="realmId") String param_objRealmId, 
			@RequestParam(value="oauth_verifier") String param_objOAuthVerifier, 
			@RequestParam(value="oauth_token") String param_objOAuthToken){
		
		g_objOAuthServices.requestTokenReady(param_objRequest, param_objRealmId, param_objOAuthVerifier, param_objOAuthToken);

	}
	
	
	@RequestMapping(value="/api/test", method=RequestMethod.GET)
	public String mainPage(){
		return "hello";
	}
	
	@RequestMapping(value="/getValue", method=RequestMethod.GET)
	public void getCustomers(){
		CustomerController c = new CustomerController();
		
	}
	
	

}

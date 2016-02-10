package com.project.quickbook.oauth.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.quickbook.oauth.util.OAuthUtils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

@RestController
public class OAuthController {
	
	@RequestMapping(value="/api/test", method=RequestMethod.GET)
	public String mainPage(){
		return "hello";
	}
	
	@RequestMapping(value="/request_token", method=RequestMethod.GET)
	public String requestTokenForAuthorization(final HttpServletResponse response,
            @RequestParam(value = "appCompanyId", required = true) String companyId) throws IOException{
		String v_obj_authUrl;
		
		OAuthConsumer v_obj_oAuthConsumer;
		OAuthUtils v_obj_oAuthUtils;
		Properties v_obj_properties; 
		OAuthProvider v_obj_oAuthProvider;
		
		v_obj_oAuthUtils = new OAuthUtils();
		v_obj_properties = v_obj_oAuthUtils.readProperties();
		v_obj_oAuthProvider = new DefaultOAuthProvider(OAuthUtils.REQUEST_TOKEN_URL, OAuthUtils.ACCESS_TOKEN_URL, OAuthUtils.AUTHORIZE_URL);
		
		try{
			v_obj_oAuthConsumer = new DefaultOAuthConsumer(v_obj_properties.getProperty("oauth_consumer_key"), v_obj_properties.getProperty("oauth_consumer_secret"));
			v_obj_authUrl = v_obj_oAuthProvider.retrieveRequestToken(v_obj_oAuthConsumer, v_obj_properties.getProperty("oauth_callbackURL"));
			return "redirect:"+v_obj_authUrl;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}

}

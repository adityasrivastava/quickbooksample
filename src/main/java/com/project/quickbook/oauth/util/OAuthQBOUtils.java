package com.project.quickbook.oauth.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuthQBOUtils{
			
	public static String OAUTH_CONSUMER_KEY;
	public static String OAUTH_CONSUMER_SECRET;
	public static String OAUTH_CALLBACK_URL;
	public static String QBO_URL;
	public static String REQUEST_TOKEN_URL;
	public static String ACCESS_TOKEN_URL;
	public static String AUTHORIZE_URL;
	public static String ACCESS_RECONNECT_TOKEN_URL;
	public static String ACCESS_TOKEN;
	public static String ACCESS_TOKEN_SECRET;
	public static String APP_TOKEN;
	public static String APP_ID;
	public static String REALM_ID;
	
	
	public String getOauth_consumer_key() {
		return OAUTH_CONSUMER_KEY;
	}
	
	@Value("${oauth_consumer_key}")
	public void setOauth_consumer_key(String oauth_consumer_key) {
		this.OAUTH_CONSUMER_KEY = oauth_consumer_key;
	}
	
	public String getOauth_consumer_secret() {
		return OAUTH_CONSUMER_SECRET;
	}
	
	@Value("${oauth_consumer_secret}")
	public void setOauth_consumer_secret(String oauth_consumer_secret) {
		this.OAUTH_CONSUMER_SECRET = oauth_consumer_secret;
	}
	
	public String getOauth_callbackUrl() {
		return OAUTH_CALLBACK_URL;
	}
	
	@Value("${oauth_callbackURL}")
	public void setOauth_callbackUrl(String oauth_callbackUrl) {
		this.OAUTH_CALLBACK_URL = oauth_callbackUrl;
	}
	
	
	public String getQbo_url() {
		return QBO_URL;
	}
	
	@Value("${qbo_url}")
	public void setQbo_url(String qbo_url) {
		this.QBO_URL = qbo_url;
	}
	
	
	public String getRequest_token_url() {
		return REQUEST_TOKEN_URL;
	}
	
	@Value("${request_token_url}")
	public void setRequest_token_url(String request_token_url) {
		this.REQUEST_TOKEN_URL = request_token_url;
	}
	
	public String getAccess_token_url() {
		return ACCESS_TOKEN_URL;
	}
	
	@Value("${access_token_url}")
	public void setAccess_token_url(String access_token_url) {
		this.ACCESS_TOKEN_URL = access_token_url;
	}
	
	public String getAuthorize_url() {
		return AUTHORIZE_URL;
	}
	
	@Value("${authorize_url}")
	public void setAuthorize_url(String authorize_url) {
		this.AUTHORIZE_URL = authorize_url;
	}
	
	
	public String getAccess_reconnect_token_url() {
		return ACCESS_RECONNECT_TOKEN_URL;
	}
	
	@Value("${access_reconnect_token_url}")
	public void setAccess_reconnect_token_url(String access_reconnect_token_url) {
		this.ACCESS_RECONNECT_TOKEN_URL = access_reconnect_token_url;
	}
	
	
	public String getAccess_token() {
		return ACCESS_TOKEN;
	}
	
	@Value("${access_token}")
	public void setAccess_token(String access_token) {
		this.ACCESS_TOKEN = access_token;
	}
	
	
	public String getAccess_token_secret() {
		return ACCESS_TOKEN_SECRET;
	}
	
	@Value("${access_token_secret}")
	public void setAccess_token_secret(String access_token_secret) {
		this.ACCESS_TOKEN_SECRET = access_token_secret;
	}
	
	
	public static String getApp_token() {
		return APP_TOKEN;
	}
	
	@Value("${app_token}")
	public void setApp_token(String app_token) {
		this.APP_TOKEN = app_token;
	}
	public static String getApp_id() {
		return APP_ID;
	}
	
	@Value("${app_id}")
	public void setApp_id(String app_id) {
		this.APP_ID = app_id;
	}
	public String getRealm_id() {
		return REALM_ID;
	}
	
	@Value("${realm_id}")
	public void setRealm_id(String realm_id) {
		this.REALM_ID = realm_id;
	}
	
	
	
	
	
	
	
	
	
	
	
}
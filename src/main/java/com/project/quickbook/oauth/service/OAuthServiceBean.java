package com.project.quickbook.oauth.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.project.quickbook.oauth.util.OAuthUtils;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;

@Service
public class OAuthServiceBean implements OAuthService{
	
	private Properties g_objProperties;
	private OAuthUtils g_objOAuthUtils;
	private OAuthConsumer g_objOAuthConsumer;

	@Override
	public String requestTokenForAuthorization(HttpServletRequest param_objRequest) {
		
		String v_objAuthUrl = null;
		
		OAuthProvider v_objOAuthProvider;
		HttpSession v_objSession;
		
		// Read required properties from application.properties & initiate OAuthProvider
		g_objOAuthUtils = new OAuthUtils();
		g_objProperties = g_objOAuthUtils.readProperties();
		v_objSession = param_objRequest.getSession();
		v_objOAuthProvider = new DefaultOAuthProvider(g_objProperties.getProperty("request_token_url"), g_objProperties.getProperty("access_token_url"), g_objProperties.getProperty("authorize_url"));
		
		// OAuth parameters and initiate request for access token
		try{
			g_objOAuthConsumer = new DefaultOAuthConsumer(g_objProperties.getProperty("oauth_consumer_key"), g_objProperties.getProperty("oauth_consumer_secret"));
			v_objAuthUrl = v_objOAuthProvider.retrieveRequestToken(g_objOAuthConsumer, g_objProperties.getProperty("oauth_callbackURL"));
			v_objSession.setAttribute("oauthConsumer", g_objOAuthConsumer);
			return v_objAuthUrl;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return v_objAuthUrl;
		
	}

	@Override
	public void requestReconnectTokenForAuthorization() {
		
		OAuthConsumer v_objOAuthConsumer;
		String v_objSignedUrl;
		URL v_objUrl;
		HttpURLConnection v_objUrlConnection;
		
		g_objOAuthUtils = new OAuthUtils();
		g_objProperties = g_objOAuthUtils.readProperties();
		v_objOAuthConsumer = new DefaultOAuthConsumer(g_objProperties.getProperty("oauth_consumer_key"), g_objProperties.getProperty("oauth_consumer_secret"));
		
		HttpParameters p = new HttpParameters();
		p.put("oauth_token_secret", g_objProperties.getProperty("accessTokenSecret"));
		p.put("oauth_token", g_objProperties.getProperty("accessToken"));
		
		v_objOAuthConsumer.setAdditionalParameters(p);
		StringBuffer sb = new StringBuffer();
		try {
			v_objSignedUrl = v_objOAuthConsumer.sign(g_objProperties.getProperty("access_reconnect_token_url"));
			System.out.println(v_objSignedUrl);
			v_objUrl = new URL(v_objSignedUrl);
			v_objUrlConnection = (HttpURLConnection) v_objUrl.openConnection();
			v_objUrlConnection.setRequestMethod("GET");
			
			
			// Print out
			System.out.println(v_objSignedUrl);
			String accesstokenresponse = "";
			String accessToken, accessTokenSecret = "";
			if (v_objUrlConnection != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						v_objUrlConnection.getInputStream()));
				
				String line;
				while ((line = rd.readLine()) != null) {
					System.out.println(line);
					sb.append(line);
					System.out.println(sb.toString());
				}
				rd.close();
				accesstokenresponse = sb.toString();
			}
		
		
		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void requestTokenReady(HttpServletRequest param_objRequest, String param_objRealmId, String param_objOAuthVerifier, String param_objOAuthToken) {
		
		HttpParameters v_objHttpParameters;
		HttpSession v_objSession;
		URL v_objUrl;
		HttpURLConnection v_objUrlConnection;
		String v_objSignedUrl;

		// 1. Sign the url 
		v_objSession = param_objRequest.getSession();
		g_objOAuthConsumer = (OAuthConsumer) v_objSession.getAttribute("oauthConsumer");
		v_objHttpParameters = new HttpParameters();
		g_objOAuthUtils = new OAuthUtils();
		g_objProperties = g_objOAuthUtils.readProperties();

		v_objHttpParameters.put("oauth_callback",OAuth.OUT_OF_BAND);
		v_objHttpParameters.put("oauth_verifier", param_objOAuthVerifier);
		g_objOAuthConsumer.setAdditionalParameters(v_objHttpParameters);
		
		try {
			// Generate signed url for retreving access token
			v_objSignedUrl = g_objOAuthConsumer.sign(g_objProperties.getProperty("access_token_url"));
			v_objUrl = new URL(v_objSignedUrl);
			
			// Retreving access token from the signed url genereated above
			v_objUrlConnection = (HttpURLConnection) v_objUrl.openConnection();
			v_objUrlConnection.setRequestMethod("GET");
			
			
			// Print out the response details
			System.out.println(v_objSignedUrl);
			String accesstokenresponse = "";
			String accessToken, accessTokenSecret = "";
			if (v_objUrlConnection != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						v_objUrlConnection.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					System.out.println(line);
					sb.append(line);
					System.out.println(sb.toString());
				}
				rd.close();
				accesstokenresponse = sb.toString();
			}
			
//			if (accesstokenresponse != null) {
//				String[] responseElements = accesstokenresponse.split("&");
//				if (responseElements.length > 1) {
//					accessToken = responseElements[1].split("=")[1];
//					accessTokenSecret = responseElements[0].split("=")[1];
//					logger.info("OAuth accessToken: " + accessToken);
//					logger.info("OAuth accessTokenSecret: " + accessTokenSecret);
//					Map<String, String> accesstokenmap = new HashMap<String, String>();
//					accesstokenmap.put("accessToken", accessToken);
//					accesstokenmap.put("accessTokenSecret", accessTokenSecret);
//					session.setAttribute("accessToken", accesstokenmap.get("accessToken"));
//					session.setAttribute("accessTokenSecret", accesstokenmap.get("accessTokenSecret"));
////					response.sendRedirect("/OauthSample/connected.jsp");
//				}
//			}
			
			

			System.out.println("paramRealmId "+param_objRealmId);
			System.out.println("oauth_verifier  "+param_objOAuthVerifier);
			System.out.println("oauth_token " + param_objOAuthToken);
			
			
		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}

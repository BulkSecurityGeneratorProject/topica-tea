package com.topica.tea.scheduled.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class FacebookScheduledTasks {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    // @Scheduled(cron = "*/5 * * * * *")
    // see: http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        //facebook.setOAuthPermissions(commaSeparetedPermissions);
        //facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
        
        try {
        	String appId = "120769275152754"; // 120769275152754
            String appSecret = "80461c4e31a429e1e11058d3dd87b564";
            Facebook facebook = new FacebookFactory().getInstance();
            
            facebook.setOAuthAppId(appId, appSecret);
            facebook.getOAuthAccessTokenInfo();
            
//            OAuthSupport oAuthSupport = new OAuthAuthorization(configuration );
//            facebook.            
//            facebook.setOAuthAccessToken(accessToken);
//            User me = facebook.getMe();
//            AccessToken accessToken = facebook.getOAuthAppAccessToken();
            //facebook.setOAuthAccessToken(new AccessToken("29e29cd161e5fa4077b55fa340e35509", null));
            
//            ResponseList<Account> accounts = facebook.getAccounts();
//            Account yourPageAccount = accounts.get(0);  // if index 0 is your page account.
//            String pageAccessToken = yourPageAccount.getAccessToken();
            
			facebook.postStatusMessage("Hello World.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

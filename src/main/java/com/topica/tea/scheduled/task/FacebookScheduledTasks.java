package com.topica.tea.scheduled.task;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.PhotoUpdate;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;
import lombok.extern.slf4j.Slf4j;


@Component
public class FacebookScheduledTasks {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    // @Scheduled(cron = "*/5 * * * * *")
    // see: http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    public void reportCurrentTime() {
        //log.info("The time is now {}", dateFormat.format(new Date()));
        //facebook.setOAuthPermissions(commaSeparetedPermissions);
        //facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
        
        try {
//        	String appId = "2065752006785957"; // 120769275152754
//            String appSecret = "22147d0b3170cacd2212c55a77d80773";
//        	String accessToken = "EAAdWyl6OZB6UBAGi6WuZC43xux10WHdTbWPeF0ofcFL0kAIDL8ZCOBy7Ay1OPEZBN3ckDWFJe6kewWL3sgNDkclnBCJpw5OG07WOQio1TTUkw83uHdWpb5Hh8M6RK2RQ7EwnLnBYqWuTLeemrAFlzhZCFDPRM1oAZD";
//            Facebook facebook = new FacebookFactory().getInstance();
//            
//          facebook.setOAuthAppId(appId, appSecret);
//          facebook.setOAuthPermissions("user_friends,email,publish_actions,public_profile");
//          facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
            
//            PostUpdate post = new PostUpdate("Hello world")
//                    .picture(new URL("http://facebook4j.org/images/hero.png"))
//                    .name("Facebook4J - A Java library for the Facebook Graph API")
//                    .caption("facebook4j.org")
//                    .description("Facebook4J is a Java library for the Facebook Graph API.");
//            	facebook.postFeed(post);
            	
//            	PhotoUpdate photo = new PhotoUpdate(new URL("http://facebook4j.org/images/hero.png"));
//            	photo.message("Test");
//            	facebook.postPhoto(photo);
//			facebook.postStatusMessage("Hello");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

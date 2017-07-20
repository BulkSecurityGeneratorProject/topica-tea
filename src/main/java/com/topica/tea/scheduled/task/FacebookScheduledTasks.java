package com.topica.tea.scheduled.task;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.PhotoUpdate;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.RawAPIResponse;
import facebook4j.ResponseList;
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
            
        	String appId = "883417718478493"; // 120769275152754
            String appSecret = "020b445591764de8f0a957976e5943fb";
        	String accessToken = "EAAMjdrdMVp0BAOpwUzWx6cJKY54SsLHxCNIUnilrc7WTV12ZAB06SDWB1vwH3ezMzSAzf2ZB93onGZAM7CZCN2Qsf6AvMKPKqPlnjnZCRmdl9cjPSv0RZA4OxdxFdVcwTjJTVCshJ0I1ZBB1yZCvX54JxKvGGYan4mFZCZAjtARmxdi99vrCdcibMn";
        	
        	Facebook facebook = new FacebookFactory().getInstance();
            
            
        	facebook.setOAuthAppId(appId, appSecret);
//        	facebook.setOAuthPermissions("publish_actions,manage_pages,pages_manage_cta,pages_manage_instant_articles,pages_show_list,publish_pages,read_page_mailboxes");
        	facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
          //facebook4j.Page page = facebook.getPage();
          //System.out.println(page);
          //facebook.postStatusMessage("Hello");
//          facebook4j.User user = facebook.getMe();
//          System.out.println(user);
          
//          PostUpdate post = new PostUpdate(new URL("http://facebook4j.org/images/hero.png"))
//                  .message("hello");
//          facebook.postFeed(post);
//          System.out.println(post);
            	
//            	PhotoUpdate photo = new PhotoUpdate(new URL("http://facebook4j.org/images/hero.png"));
//            	photo.message("Test");
//            	facebook.postPhoto(photo);
//			facebook.postStatusMessage("Hello");
        	
//          Map<String, String> parameters = new HashMap<>();
//          parameters.put("message", "hello world 23");
//          parameters.put("link", "http://vnexpress.net/tin-tuc/the-gioi/may-bay-cho-282-khach-ha-canh-khan-vi-no-lop-3614925.html");
//          RawAPIResponse response = facebook.callPostAPI("/v2.9/589542831195267/feed", parameters);
//          System.out.println(response);
        	
//        	ResponseList<Account> lstAccounts = facebook.getAccounts();
//        	for (Account account : lstAccounts) {
//        		String pageAccessToken = account.getAccessToken();
//        		String pageId = account.getId();
//        		
//        		if (StringUtils.equals(pageId, "1112889808775321")) {
//        			facebook.setOAuthAccessToken(new AccessToken(pageAccessToken, null));
//        			Map<String, String> parameters = new HashMap<>();
//        			parameters.put("message", "--- Hello TEA ---");
//        			parameters.put("link", "http://vnexpress.net/tin-tuc/the-gioi/may-bay-cho-282-khach-ha-canh-khan-vi-no-lop-3614925.html");
//        			RawAPIResponse response = facebook.callPostAPI("/v2.9/1112889808775321/feed", parameters);
//        			System.out.println(response);
//        		}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

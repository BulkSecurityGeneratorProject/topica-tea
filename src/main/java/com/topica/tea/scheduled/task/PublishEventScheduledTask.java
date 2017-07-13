package com.topica.tea.scheduled.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.topica.tea.domain.enumeration.EventStatus;
import com.topica.tea.service.EventService;
import com.topica.tea.service.FanpageService;
import com.topica.tea.service.dto.EventDTO;


@Component
public class PublishEventScheduledTask {
	private final Logger log = LoggerFactory.getLogger(PublishEventScheduledTask.class);
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private final EventService eventService;
	
	private final FanpageService fanpageService;
	
	public PublishEventScheduledTask(EventService eventService, FanpageService fanpageService) {
        this.eventService = eventService;
        this.fanpageService = fanpageService;
    }
	
    @Scheduled(fixedRate = 60000)
    // @Scheduled(cron = "*/5 * * * * *")
    // see: http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        //facebook.setOAuthPermissions(commaSeparetedPermissions);
        //facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
        
        try {
        	EventDTO eventDTO = eventService.findOneByEventStatus(EventStatus.MANAGER_APPROVE);
        	
        	if (eventDTO == null) {
        		log.info("No new event");
        		return;
        	}
        	
        	// Update status -> Published
        	/**
             * updateStatus a event.
             *
             * @param eventDTO the entity to editor
             * @return the persisted entity
             */
        	eventService.updateStatus(eventDTO.getId(), EventStatus.PUBLISHED.toString());
        	
        	// Send facebook and update status
        	fanpageService.postToFanpage(eventDTO);
        	
//        	String appId = "2065752006785957"; // 120769275152754
//            String appSecret = "22147d0b3170cacd2212c55a77d80773";
//        	String accessToken = "EAAdWyl6OZB6UBAGi6WuZC43xux10WHdTbWPeF0ofcFL0kAIDL8ZCOBy7Ay1OPEZBN3ckDWFJe6kewWL3sgNDkclnBCJpw5OG07WOQio1TTUkw83uHdWpb5Hh8M6RK2RQ7EwnLnBYqWuTLeemrAFlzhZCFDPRM1oAZD";
//            Facebook facebook = new FacebookFactory().getInstance();
//
//            facebook.setOAuthAppId(appId, appSecret);
//            facebook.setOAuthPermissions("user_friends,email,publish_actions,public_profile");
//            facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
//
//			facebook.postStatusMessage("Hello");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}

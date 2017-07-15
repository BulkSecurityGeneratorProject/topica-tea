package com.topica.tea.service;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.topica.tea.service.dto.ArticleDTO;
import com.topica.tea.service.dto.ChannelProductDTO;
import com.topica.tea.service.dto.EventDTO;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.auth.AccessToken;

/**
 * Service Interface for managing Fanpage.
 */
@Service
public class FanpageService {

	private final Logger log = LoggerFactory.getLogger(FanpageService.class);

    public FanpageService() {
    }

//    @Async
    public void postToFanpage(EventDTO eventDTO) {
        log.debug("Method postToFanpage, eventDTO={}", eventDTO);

        // Loop via channel product
        if (null == eventDTO.getChannelProducts()) {
        	return;
        }
        
        if (StringUtils.isEmpty(eventDTO.getArticle().getFanpageContent())) {
        	log.warn("Method postToFacebook, fanpageContent is emplty");
        	return;
        }
        
        for (ChannelProductDTO item : eventDTO.getChannelProducts()) {
			if (StringUtils.equals(item.getAdsType().getName(), "Fanpage")) {
				log.debug("Post to facebook, item={}, Article={}", eventDTO, eventDTO.getArticle());
				postToFacebook(item, eventDTO.getArticle());
			}
		}
        
    }
    
    private void postToFacebook(ChannelProductDTO item, ArticleDTO articleDTO) {
    	try {
        	String appId = item.getAppId(); // 120769275152754
            String appSecret = item.getAppSecret();
        	String accessToken = item.getAppAccessToken();
            Facebook facebook = new FacebookFactory().getInstance();

            facebook.setOAuthAppId(appId, appSecret);
            facebook.setOAuthPermissions("user_friends,email,publish_actions,public_profile");
            facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
            
            
			//facebook.postStatusMessage(content);
			
			PostUpdate post = new PostUpdate(new URL(articleDTO.getFanpageLink()))
                    .message(articleDTO.getFanpageContent());
			facebook.postFeed(post);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}

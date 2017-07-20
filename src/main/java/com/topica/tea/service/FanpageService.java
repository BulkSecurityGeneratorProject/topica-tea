package com.topica.tea.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.topica.tea.service.dto.ArticleDTO;
import com.topica.tea.service.dto.ChannelProductDTO;
import com.topica.tea.service.dto.EventDTO;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.ResponseList;
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
//        	String appId = item.getAppId(); // 120769275152754
//            String appSecret = item.getAppSecret();
//        	String accessToken = item.getAppAccessToken();
        	
        	String appId = "883417718478493"; // 120769275152754
            String appSecret = "020b445591764de8f0a957976e5943fb";
        	String accessToken = "EAAMjdrdMVp0BAOpwUzWx6cJKY54SsLHxCNIUnilrc7WTV12ZAB06SDWB1vwH3ezMzSAzf2ZB93onGZAM7CZCN2Qsf6AvMKPKqPlnjnZCRmdl9cjPSv0RZA4OxdxFdVcwTjJTVCshJ0I1ZBB1yZCvX54JxKvGGYan4mFZCZAjtARmxdi99vrCdcibMn";
        	
        	String pageId = item.getPageId();
        	log.debug("method postToFacebook, appId={}, appSecret={}, accessToken={}, pageId={}", appId, appSecret, accessToken, pageId);
        	
        	if (StringUtils.isEmpty(pageId)) {
        		log.warn("PageId is NULL, channelProduct: {}", item);
        	}
        	
            Facebook facebook = new FacebookFactory().getInstance();

            facebook.setOAuthAppId(appId, appSecret);
            //facebook.setOAuthPermissions("publish_actions,manage_pages,pages_manage_cta,pages_manage_instant_articles,pages_show_list,publish_pages,read_page_mailboxes");
            facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
            
            Map<String, String> parameters = new HashMap<>();
            parameters.put("message", articleDTO.getFanpageContent());
            parameters.put("link", articleDTO.getFanpageLink());
            RawAPIResponse response = null;
            boolean isPostToFeed = false;
            if (StringUtils.isNotEmpty(pageId)) {
            	ResponseList<Account> lstAccounts = facebook.getAccounts();
            	for (Account account : lstAccounts) {
            		String pageAccessToken = account.getAccessToken();
            		String pageIdQuery = account.getId();
            		
            		if (StringUtils.equals(pageId, pageIdQuery)) {
            			facebook.setOAuthAccessToken(new AccessToken(pageAccessToken, null));
            			response = facebook.callPostAPI("/v2.9/" + pageId + "/feed", parameters);
            			log.info(response.toString());
            			isPostToFeed = true;
            		}
    			}
            } 
            
            if (isPostToFeed == false) {
            	log.warn("Can not post to PageId {}, please check it again", pageId);
            }
            //else {
            	//response = facebook.callPostAPI("/v2.9/me/feed", parameters);
            //}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}

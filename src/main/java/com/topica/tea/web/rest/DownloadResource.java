package com.topica.tea.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.topica.tea.service.ChannelProductService;
import com.topica.tea.service.ScheduleService;
import com.topica.tea.web.rest.util.HeaderUtil;
import com.topica.tea.web.rest.util.PaginationUtil;
import com.topica.tea.service.dto.ChannelProductDTO;
import com.topica.tea.service.dto.ScheduleDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Schedule.
 */
@RestController
@RequestMapping("/api")
public class DownloadResource {

    private final Logger log = LoggerFactory.getLogger(DownloadResource.class);

    private static final String ENTITY_NAME = "download";

    private final ChannelProductService channelProductService;

    public DownloadResource(ChannelProductService channelProductService) {
    	this.channelProductService = channelProductService;
    }

    /**
     * POST  /schedules : Create a new schedule.
     *
     * @param scheduleDTO the scheduleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new scheduleDTO, or with status 400 (Bad Request) if the schedule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @GetMapping("/download/csv")
    @Timed
    public ResponseEntity<byte[]> downloadCSV(HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to downloadCSV");
        
        String[] headers = new String[]{"STT", "Name", "Link", "Traffice", "TrafficeType", "Product", "AdsType", "WidgetCode"};
        String lineHeader = StringUtils.join(headers, ",");
        
        StringBuffer data = new StringBuffer();
        data.append(lineHeader).append("\n");

        // Get data
        List<ChannelProductDTO> lstChannels = channelProductService.findAll();
        for (ChannelProductDTO c : lstChannels) {
        	String id = String.valueOf(c.getId());
        	String name = String.valueOf(c.getName());
        	String link = StringUtils.isNotEmpty(c.getLink()) ? c.getLink() : StringUtils.EMPTY;
        	String traffice = c.getTraffic() != null ? String.valueOf(c.getTraffic()) : StringUtils.EMPTY;
        	String trafficeType = c.getTrafficType() != null ? String.valueOf(c.getTrafficType()) : StringUtils.EMPTY;
        	String product = c.getProduct() != null ? c.getProduct().getProductName() : StringUtils.EMPTY;
        	String adsType = c.getAdsType() != null ? c.getAdsType().getName() : StringUtils.EMPTY;
        	String widgetCode = buildWidgetCode(request, c);
			String[] itemArr = new String[]{id, name, link, traffice, trafficeType, product, adsType, widgetCode};
			data.append(StringUtils.join(itemArr, ",")).append("\n");
		}
        
        
        byte[] output = data.toString().getBytes();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf("text/html"));
        responseHeaders.setContentLength(output.length);
        responseHeaders.set("Content-disposition", "attachment; filename=channel-product.csv");

        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }
    
    private String buildWidgetCode(HttpServletRequest request, ChannelProductDTO c) {
    	if (c.getHtmlTemplate() == null) {
    		return "";
    	}
    	
    	String path = "http://" + request.getServerName() + ":" + request.getServerPort();
    	String widget = "";
    	if (c.getAdsType().getId() == 2l) {
    		widget = path + "/api/inject-event?channelProductId=" + c.getId();
    	// Mail
    	} else if (c.getAdsType().getId() == 1l) {
    		widget = "<script id=\"topica-widget-script\" src=\"" + path + "/content/widget/inject-event.js\" " +
    				"data-channelProductId=\"" +  c.getId() + "\" type=\"text/javascript\">" +
    				"</script>"
    				+ "<div id=\"topica-widget-container\"></div>";
    	}
    	
    	// http://localhost:9301
    	//String widget = String.format("", args);
    	return widget;
    }
}

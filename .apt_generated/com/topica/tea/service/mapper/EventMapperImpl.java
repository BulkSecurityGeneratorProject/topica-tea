package com.topica.tea.service.mapper;

import com.topica.tea.domain.Article;
import com.topica.tea.domain.ChannelProduct;
import com.topica.tea.domain.Event;
import com.topica.tea.domain.Product;
import com.topica.tea.domain.Question;
import com.topica.tea.domain.enumeration.AmplifyType;
import com.topica.tea.domain.enumeration.PriorityGroup;
import com.topica.tea.service.dto.ChannelProductDTO;
import com.topica.tea.service.dto.EventDTO;
import com.topica.tea.service.dto.ProductDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-16T15:23:27+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ChannelProductMapper channelProductMapper;

    @Override
    public List<EventDTO> toDto(List<Event> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>();
        for ( Event event : arg0 ) {
            list.add( toDto( event ) );
        }

        return list;
    }

    @Override
    public List<Event> toEntity(List<EventDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>();
        for ( EventDTO eventDTO : arg0 ) {
            list.add( toEntity( eventDTO ) );
        }

        return list;
    }

    @Override
    public EventDTO toDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDTO eventDTO_ = new EventDTO();

        eventDTO_.setArticleId( eventArticleId( event ) );
        eventDTO_.setQuestionId( eventQuestionId( event ) );
        List<AmplifyType> list = event.getAmplifyType();
        if ( list != null ) {
            eventDTO_.setAmplifyType(       new ArrayList<AmplifyType>( list )
            );
        }
        eventDTO_.setArticle( articleMapper.toDto( event.getArticle() ) );
        Set<ChannelProductDTO> set = channelProductSetToChannelProductDTOSet( event.getChannelProducts() );
        if ( set != null ) {
            eventDTO_.setChannelProducts( set );
        }
        eventDTO_.setContent( event.getContent() );
        eventDTO_.setDescription( event.getDescription() );
        eventDTO_.setEventLevel( event.getEventLevel() );
        eventDTO_.setEventStatus( event.getEventStatus() );
        eventDTO_.setId( event.getId() );
        eventDTO_.setIsFanpagePublished( event.getIsFanpagePublished() );
        eventDTO_.setIsHotEvent( event.getIsHotEvent() );
        eventDTO_.setMaterialFanpage( event.getMaterialFanpage() );
        eventDTO_.setMaterialLandingpage( event.getMaterialLandingpage() );
        eventDTO_.setMaterialMail( event.getMaterialMail() );
        eventDTO_.setName( event.getName() );
        List<PriorityGroup> list_ = event.getPriorityGroup();
        if ( list_ != null ) {
            eventDTO_.setPriorityGroup(       new HashSet<PriorityGroup>( list_ )
            );
        }
        Set<ProductDTO> set_ = productSetToProductDTOSet( event.getProducts() );
        if ( set_ != null ) {
            eventDTO_.setProducts( set_ );
        }
        eventDTO_.setQuestion( questionMapper.toDto( event.getQuestion() ) );
        eventDTO_.setReason( event.getReason() );
        eventDTO_.setSchedule( event.getSchedule() );

        return eventDTO_;
    }

    @Override
    public Event toEntity(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        Event event_ = new Event();

        event_.setQuestion( questionMapper.fromId( eventDTO.getQuestionId() ) );
        event_.setArticle( articleMapper.fromId( eventDTO.getArticleId() ) );
        List<AmplifyType> list = eventDTO.getAmplifyType();
        if ( list != null ) {
            event_.setAmplifyType(       new ArrayList<AmplifyType>( list )
            );
        }
        Set<ChannelProduct> set = channelProductDTOSetToChannelProductSet( eventDTO.getChannelProducts() );
        if ( set != null ) {
            event_.setChannelProducts( set );
        }
        event_.setContent( eventDTO.getContent() );
        event_.setDescription( eventDTO.getDescription() );
        event_.setEventLevel( eventDTO.getEventLevel() );
        event_.setEventStatus( eventDTO.getEventStatus() );
        event_.setId( eventDTO.getId() );
        event_.setIsFanpagePublished( eventDTO.getIsFanpagePublished() );
        event_.setIsHotEvent( eventDTO.getIsHotEvent() );
        event_.setMaterialFanpage( eventDTO.getMaterialFanpage() );
        event_.setMaterialLandingpage( eventDTO.getMaterialLandingpage() );
        event_.setMaterialMail( eventDTO.getMaterialMail() );
        event_.setName( eventDTO.getName() );
        Set<PriorityGroup> set_ = eventDTO.getPriorityGroup();
        if ( set_ != null ) {
            event_.setPriorityGroup(       new ArrayList<PriorityGroup>( set_ )
            );
        }
        Set<Product> set__ = productDTOSetToProductSet( eventDTO.getProducts() );
        if ( set__ != null ) {
            event_.setProducts( set__ );
        }
        event_.setReason( eventDTO.getReason() );
        event_.setSchedule( eventDTO.getSchedule() );

        return event_;
    }

    private Long eventArticleId(Event event) {

        if ( event == null ) {
            return null;
        }
        Article article = event.getArticle();
        if ( article == null ) {
            return null;
        }
        Long id = article.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long eventQuestionId(Event event) {

        if ( event == null ) {
            return null;
        }
        Question question = event.getQuestion();
        if ( question == null ) {
            return null;
        }
        Long id = question.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<ChannelProductDTO> channelProductSetToChannelProductDTOSet(Set<ChannelProduct> set) {
        if ( set == null ) {
            return null;
        }

        Set<ChannelProductDTO> set_ = new HashSet<ChannelProductDTO>();
        for ( ChannelProduct channelProduct : set ) {
            set_.add( channelProductMapper.toDto( channelProduct ) );
        }

        return set_;
    }

    protected Set<ProductDTO> productSetToProductDTOSet(Set<Product> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProductDTO> set_ = new HashSet<ProductDTO>();
        for ( Product product : set ) {
            set_.add( productMapper.toDto( product ) );
        }

        return set_;
    }

    protected Set<ChannelProduct> channelProductDTOSetToChannelProductSet(Set<ChannelProductDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<ChannelProduct> set_ = new HashSet<ChannelProduct>();
        for ( ChannelProductDTO channelProductDTO : set ) {
            set_.add( channelProductMapper.toEntity( channelProductDTO ) );
        }

        return set_;
    }

    protected Set<Product> productDTOSetToProductSet(Set<ProductDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Product> set_ = new HashSet<Product>();
        for ( ProductDTO productDTO : set ) {
            set_.add( productMapper.toEntity( productDTO ) );
        }

        return set_;
    }
}

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
    date = "2017-07-19T13:59:49+0700",
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
    public List<Event> toEntity(List<EventDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>();
        for ( EventDTO eventDTO : dtoList ) {
            list.add( toEntity( eventDTO ) );
        }

        return list;
    }

    @Override
    public List<EventDTO> toDto(List<Event> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EventDTO> list = new ArrayList<EventDTO>();
        for ( Event event : entityList ) {
            list.add( toDto( event ) );
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
        eventDTO_.setId( event.getId() );
        eventDTO_.setName( event.getName() );
        eventDTO_.setDescription( event.getDescription() );
        eventDTO_.setContent( event.getContent() );
        eventDTO_.setEventStatus( event.getEventStatus() );
        eventDTO_.setEventLevel( event.getEventLevel() );
        List<AmplifyType> list = event.getAmplifyType();
        if ( list != null ) {
            eventDTO_.setAmplifyType(       new ArrayList<AmplifyType>( list )
            );
        }
        eventDTO_.setSchedule( event.getSchedule() );
        eventDTO_.setQuestion( questionMapper.toDto( event.getQuestion() ) );
        Set<ProductDTO> set = productSetToProductDTOSet( event.getProducts() );
        if ( set != null ) {
            eventDTO_.setProducts( set );
        }
        List<PriorityGroup> list_ = event.getPriorityGroup();
        if ( list_ != null ) {
            eventDTO_.setPriorityGroup(       new HashSet<PriorityGroup>( list_ )
            );
        }
        eventDTO_.setArticle( articleMapper.toDto( event.getArticle() ) );
        Set<ChannelProductDTO> set_ = channelProductSetToChannelProductDTOSet( event.getChannelProducts() );
        if ( set_ != null ) {
            eventDTO_.setChannelProducts( set_ );
        }
        eventDTO_.setIsHotEvent( event.getIsHotEvent() );
        eventDTO_.setIsFanpagePublished( event.getIsFanpagePublished() );
        eventDTO_.setReason( event.getReason() );
        eventDTO_.setMaterialLandingpage( event.getMaterialLandingpage() );
        eventDTO_.setMaterialFanpage( event.getMaterialFanpage() );
        eventDTO_.setMaterialMail( event.getMaterialMail() );

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
        Set<ChannelProduct> set = channelProductDTOSetToChannelProductSet( eventDTO.getChannelProducts() );
        if ( set != null ) {
            event_.setChannelProducts( set );
        }
        Set<Product> set_ = productDTOSetToProductSet( eventDTO.getProducts() );
        if ( set_ != null ) {
            event_.setProducts( set_ );
        }
        event_.setId( eventDTO.getId() );
        event_.setName( eventDTO.getName() );
        event_.setDescription( eventDTO.getDescription() );
        event_.setContent( eventDTO.getContent() );
        event_.setEventStatus( eventDTO.getEventStatus() );
        event_.setEventLevel( eventDTO.getEventLevel() );
        List<AmplifyType> list = eventDTO.getAmplifyType();
        if ( list != null ) {
            event_.setAmplifyType(       new ArrayList<AmplifyType>( list )
            );
        }
        Set<PriorityGroup> set__ = eventDTO.getPriorityGroup();
        if ( set__ != null ) {
            event_.setPriorityGroup(       new ArrayList<PriorityGroup>( set__ )
            );
        }
        event_.setSchedule( eventDTO.getSchedule() );
        event_.setIsHotEvent( eventDTO.getIsHotEvent() );
        event_.setIsFanpagePublished( eventDTO.getIsFanpagePublished() );
        event_.setMaterialLandingpage( eventDTO.getMaterialLandingpage() );
        event_.setMaterialFanpage( eventDTO.getMaterialFanpage() );
        event_.setMaterialMail( eventDTO.getMaterialMail() );
        event_.setReason( eventDTO.getReason() );

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

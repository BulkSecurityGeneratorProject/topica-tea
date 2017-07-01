package com.topica.tea.service.mapper;

import com.topica.tea.domain.Article;
import com.topica.tea.domain.Event;
import com.topica.tea.domain.Product;
import com.topica.tea.domain.Question;
import com.topica.tea.domain.enumeration.PriorityGroup;
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
    date = "2017-07-01T13:57:57+0700",
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
        eventDTO_.setAmplifyType( event.getAmplifyType() );
        eventDTO_.setContent( event.getContent() );
        eventDTO_.setDescription( event.getDescription() );
        eventDTO_.setEventLevel( event.getEventLevel() );
        eventDTO_.setEventStatus( event.getEventStatus() );
        eventDTO_.setId( event.getId() );
        eventDTO_.setName( event.getName() );
        List<PriorityGroup> list = event.getPriorityGroup();
        if ( list != null ) {
            eventDTO_.setPriorityGroup(       new HashSet<PriorityGroup>( list )
            );
        }
        Set<ProductDTO> set = productSetToProductDTOSet( event.getProducts() );
        if ( set != null ) {
            eventDTO_.setProducts( set );
        }
        eventDTO_.setQuestion( questionMapper.toDto( event.getQuestion() ) );
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
        event_.setAmplifyType( eventDTO.getAmplifyType() );
        event_.setContent( eventDTO.getContent() );
        event_.setDescription( eventDTO.getDescription() );
        event_.setEventLevel( eventDTO.getEventLevel() );
        event_.setEventStatus( eventDTO.getEventStatus() );
        event_.setId( eventDTO.getId() );
        event_.setName( eventDTO.getName() );
        Set<PriorityGroup> set = eventDTO.getPriorityGroup();
        if ( set != null ) {
            event_.setPriorityGroup(       new ArrayList<PriorityGroup>( set )
            );
        }
        Set<Product> set_ = productDTOSetToProductSet( eventDTO.getProducts() );
        if ( set_ != null ) {
            event_.setProducts( set_ );
        }
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

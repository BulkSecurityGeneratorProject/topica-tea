package com.topica.tea.service.mapper;

import com.topica.tea.domain.Brandkey;
import com.topica.tea.domain.ChannelGroup;
import com.topica.tea.domain.Question;
import com.topica.tea.service.dto.BrandkeyDTO;
import com.topica.tea.service.dto.QuestionDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-06-30T09:10:40+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Autowired
    private ChannelGroupMapper channelGroupMapper;
    @Autowired
    private BrandkeyMapper brandkeyMapper;

    @Override
    public List<Question> toEntity(List<QuestionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Question> list = new ArrayList<Question>();
        for ( QuestionDTO questionDTO : dtoList ) {
            list.add( toEntity( questionDTO ) );
        }

        return list;
    }

    @Override
    public List<QuestionDTO> toDto(List<Question> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<QuestionDTO> list = new ArrayList<QuestionDTO>();
        for ( Question question : entityList ) {
            list.add( toDto( question ) );
        }

        return list;
    }

    @Override
    public QuestionDTO toDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDTO questionDTO_ = new QuestionDTO();

        questionDTO_.setScaleId( questionScaleId( question ) );
        questionDTO_.setRoleId( questionRoleId( question ) );
        questionDTO_.setInviteeId( questionInviteeId( question ) );
        questionDTO_.setId( question.getId() );
        questionDTO_.setIsMeatContent( question.isIsMeatContent() );
        questionDTO_.setEventType( question.getEventType() );
        questionDTO_.setAmplifyType( question.getAmplifyType() );
        Set<BrandkeyDTO> set = brandkeySetToBrandkeyDTOSet( question.getBrandkeys() );
        if ( set != null ) {
            questionDTO_.setBrandkeys( set );
        }

        return questionDTO_;
    }

    @Override
    public Question toEntity(QuestionDTO questionDTO) {
        if ( questionDTO == null ) {
            return null;
        }

        Question question_ = new Question();

        question_.setScale( channelGroupMapper.fromId( questionDTO.getScaleId() ) );
        question_.setRole( channelGroupMapper.fromId( questionDTO.getRoleId() ) );
        question_.setInvitee( channelGroupMapper.fromId( questionDTO.getInviteeId() ) );
        question_.setId( questionDTO.getId() );
        question_.setIsMeatContent( questionDTO.isIsMeatContent() );
        question_.setEventType( questionDTO.getEventType() );
        question_.setAmplifyType( questionDTO.getAmplifyType() );
        Set<Brandkey> set = brandkeyDTOSetToBrandkeySet( questionDTO.getBrandkeys() );
        if ( set != null ) {
            question_.setBrandkeys( set );
        }

        return question_;
    }

    private Long questionScaleId(Question question) {

        if ( question == null ) {
            return null;
        }
        ChannelGroup scale = question.getScale();
        if ( scale == null ) {
            return null;
        }
        Long id = scale.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long questionRoleId(Question question) {

        if ( question == null ) {
            return null;
        }
        ChannelGroup role = question.getRole();
        if ( role == null ) {
            return null;
        }
        Long id = role.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long questionInviteeId(Question question) {

        if ( question == null ) {
            return null;
        }
        ChannelGroup invitee = question.getInvitee();
        if ( invitee == null ) {
            return null;
        }
        Long id = invitee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<BrandkeyDTO> brandkeySetToBrandkeyDTOSet(Set<Brandkey> set) {
        if ( set == null ) {
            return null;
        }

        Set<BrandkeyDTO> set_ = new HashSet<BrandkeyDTO>();
        for ( Brandkey brandkey : set ) {
            set_.add( brandkeyMapper.toDto( brandkey ) );
        }

        return set_;
    }

    protected Set<Brandkey> brandkeyDTOSetToBrandkeySet(Set<BrandkeyDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Brandkey> set_ = new HashSet<Brandkey>();
        for ( BrandkeyDTO brandkeyDTO : set ) {
            set_.add( brandkeyMapper.toEntity( brandkeyDTO ) );
        }

        return set_;
    }
}

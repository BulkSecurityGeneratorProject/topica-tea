package com.topica.tea.service.mapper;

import com.topica.tea.domain.Article;
import com.topica.tea.service.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-02T11:05:05+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntity(ArticleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( dto.getId() );
        article.setTitle( dto.getTitle() );
        article.setContent( dto.getContent() );
        article.setType( dto.getType() );
        article.setStatus( dto.getStatus() );

        return article;
    }

    @Override
    public ArticleDTO toDto(Article entity) {
        if ( entity == null ) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setId( entity.getId() );
        articleDTO.setTitle( entity.getTitle() );
        articleDTO.setContent( entity.getContent() );
        articleDTO.setType( entity.getType() );
        articleDTO.setStatus( entity.getStatus() );

        return articleDTO;
    }

    @Override
    public List<Article> toEntity(List<ArticleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Article> list = new ArrayList<Article>();
        for ( ArticleDTO articleDTO : dtoList ) {
            list.add( toEntity( articleDTO ) );
        }

        return list;
    }

    @Override
    public List<ArticleDTO> toDto(List<Article> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ArticleDTO> list = new ArrayList<ArticleDTO>();
        for ( Article article : entityList ) {
            list.add( toDto( article ) );
        }

        return list;
    }
}

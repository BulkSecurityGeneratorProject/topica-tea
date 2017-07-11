package com.topica.tea.service.mapper;

import com.topica.tea.domain.Article;
import com.topica.tea.service.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-10T22:02:25+0700",
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
        article.setSubject1( dto.getSubject1() );
        article.setContent1( dto.getContent1() );
        article.setUrlLink1( dto.getUrlLink1() );
        article.setImageLink1( dto.getImageLink1() );
        article.setSubject2( dto.getSubject2() );
        article.setContent2( dto.getContent2() );
        article.setUrlLink2( dto.getUrlLink2() );
        article.setImageLink2( dto.getImageLink2() );
        article.setSubject3( dto.getSubject3() );
        article.setContent3( dto.getContent3() );
        article.setUrlLink3( dto.getUrlLink3() );
        article.setImageLink3( dto.getImageLink3() );
        article.setImageFilename( dto.getImageFilename() );
        article.setVideoLink( dto.getVideoLink() );
        article.setVideoCaption( dto.getVideoCaption() );
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
        articleDTO.setType( entity.getType() );
        articleDTO.setStatus( entity.getStatus() );
        articleDTO.setImageFilename( entity.getImageFilename() );
        articleDTO.setVideoLink( entity.getVideoLink() );
        articleDTO.setVideoCaption( entity.getVideoCaption() );
        articleDTO.setContent1( entity.getContent1() );
        articleDTO.setSubject1( entity.getSubject1() );
        articleDTO.setUrlLink1( entity.getUrlLink1() );
        articleDTO.setImageLink1( entity.getImageLink1() );
        articleDTO.setContent2( entity.getContent2() );
        articleDTO.setSubject2( entity.getSubject2() );
        articleDTO.setUrlLink2( entity.getUrlLink2() );
        articleDTO.setImageLink2( entity.getImageLink2() );
        articleDTO.setContent3( entity.getContent3() );
        articleDTO.setSubject3( entity.getSubject3() );
        articleDTO.setUrlLink3( entity.getUrlLink3() );
        articleDTO.setImageLink3( entity.getImageLink3() );

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

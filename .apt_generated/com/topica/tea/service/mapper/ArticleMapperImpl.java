package com.topica.tea.service.mapper;

import com.topica.tea.domain.Article;
import com.topica.tea.service.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-10T14:59:54+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleDTO toDto(Article arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setContent1( arg0.getContent1() );
        articleDTO.setContent2( arg0.getContent2() );
        articleDTO.setContent3( arg0.getContent3() );
        articleDTO.setId( arg0.getId() );
        articleDTO.setImageFilename( arg0.getImageFilename() );
        articleDTO.setImageLink1( arg0.getImageLink1() );
        articleDTO.setImageLink2( arg0.getImageLink2() );
        articleDTO.setImageLink3( arg0.getImageLink3() );
        articleDTO.setStatus( arg0.getStatus() );
        articleDTO.setSubject1( arg0.getSubject1() );
        articleDTO.setSubject2( arg0.getSubject2() );
        articleDTO.setSubject3( arg0.getSubject3() );
        articleDTO.setTitle( arg0.getTitle() );
        articleDTO.setType( arg0.getType() );
        articleDTO.setUrlLink1( arg0.getUrlLink1() );
        articleDTO.setUrlLink2( arg0.getUrlLink2() );
        articleDTO.setUrlLink3( arg0.getUrlLink3() );
        articleDTO.setVideoCaption( arg0.getVideoCaption() );
        articleDTO.setVideoLink( arg0.getVideoLink() );

        return articleDTO;
    }

    @Override
    public List<ArticleDTO> toDto(List<Article> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ArticleDTO> list = new ArrayList<ArticleDTO>();
        for ( Article article : arg0 ) {
            list.add( toDto( article ) );
        }

        return list;
    }

    @Override
    public Article toEntity(ArticleDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Article article = new Article();

        article.setContent1( arg0.getContent1() );
        article.setContent2( arg0.getContent2() );
        article.setContent3( arg0.getContent3() );
        article.setId( arg0.getId() );
        article.setImageFilename( arg0.getImageFilename() );
        article.setImageLink1( arg0.getImageLink1() );
        article.setImageLink2( arg0.getImageLink2() );
        article.setImageLink3( arg0.getImageLink3() );
        article.setStatus( arg0.getStatus() );
        article.setSubject1( arg0.getSubject1() );
        article.setSubject2( arg0.getSubject2() );
        article.setSubject3( arg0.getSubject3() );
        article.setTitle( arg0.getTitle() );
        article.setType( arg0.getType() );
        article.setUrlLink1( arg0.getUrlLink1() );
        article.setUrlLink2( arg0.getUrlLink2() );
        article.setUrlLink3( arg0.getUrlLink3() );
        article.setVideoCaption( arg0.getVideoCaption() );
        article.setVideoLink( arg0.getVideoLink() );

        return article;
    }

    @Override
    public List<Article> toEntity(List<ArticleDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Article> list = new ArrayList<Article>();
        for ( ArticleDTO articleDTO : arg0 ) {
            list.add( toEntity( articleDTO ) );
        }

        return list;
    }
}

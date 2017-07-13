package com.topica.tea.service.mapper;

import com.topica.tea.domain.AdsType;
import com.topica.tea.domain.ChannelProduct;
import com.topica.tea.domain.Product;
import com.topica.tea.service.dto.ChannelProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-07-13T06:34:42+0700",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ChannelProductMapperImpl implements ChannelProductMapper {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AdsTypeMapper adsTypeMapper;

    @Override
    public List<ChannelProductDTO> toDto(List<ChannelProduct> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ChannelProductDTO> list = new ArrayList<ChannelProductDTO>();
        for ( ChannelProduct channelProduct : arg0 ) {
            list.add( toDto( channelProduct ) );
        }

        return list;
    }

    @Override
    public List<ChannelProduct> toEntity(List<ChannelProductDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ChannelProduct> list = new ArrayList<ChannelProduct>();
        for ( ChannelProductDTO channelProductDTO : arg0 ) {
            list.add( toEntity( channelProductDTO ) );
        }

        return list;
    }

    @Override
    public ChannelProductDTO toDto(ChannelProduct channelProduct) {
        if ( channelProduct == null ) {
            return null;
        }

        ChannelProductDTO channelProductDTO_ = new ChannelProductDTO();

        channelProductDTO_.setProductId( channelProductProductId( channelProduct ) );
        channelProductDTO_.setAdsTypeId( channelProductAdsTypeId( channelProduct ) );
        channelProductDTO_.setAdsType( adsTypeMapper.toDto( channelProduct.getAdsType() ) );
        channelProductDTO_.setAppAccessToken( channelProduct.getAppAccessToken() );
        channelProductDTO_.setAppId( channelProduct.getAppId() );
        channelProductDTO_.setAppSecret( channelProduct.getAppSecret() );
        channelProductDTO_.setId( channelProduct.getId() );
        channelProductDTO_.setLink( channelProduct.getLink() );
        channelProductDTO_.setProduct( productMapper.toDto( channelProduct.getProduct() ) );
        channelProductDTO_.setTraffic( channelProduct.getTraffic() );
        channelProductDTO_.setTrafficType( channelProduct.getTrafficType() );

        return channelProductDTO_;
    }

    @Override
    public ChannelProduct toEntity(ChannelProductDTO channelProductDTO) {
        if ( channelProductDTO == null ) {
            return null;
        }

        ChannelProduct channelProduct_ = new ChannelProduct();

        channelProduct_.setProduct( productMapper.fromId( channelProductDTO.getProductId() ) );
        channelProduct_.setAdsType( adsTypeMapper.fromId( channelProductDTO.getAdsTypeId() ) );
        channelProduct_.setAppAccessToken( channelProductDTO.getAppAccessToken() );
        channelProduct_.setAppId( channelProductDTO.getAppId() );
        channelProduct_.setAppSecret( channelProductDTO.getAppSecret() );
        channelProduct_.setId( channelProductDTO.getId() );
        channelProduct_.setLink( channelProductDTO.getLink() );
        channelProduct_.setTraffic( channelProductDTO.getTraffic() );
        channelProduct_.setTrafficType( channelProductDTO.getTrafficType() );

        return channelProduct_;
    }

    private Long channelProductProductId(ChannelProduct channelProduct) {

        if ( channelProduct == null ) {
            return null;
        }
        Product product = channelProduct.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long channelProductAdsTypeId(ChannelProduct channelProduct) {

        if ( channelProduct == null ) {
            return null;
        }
        AdsType adsType = channelProduct.getAdsType();
        if ( adsType == null ) {
            return null;
        }
        Long id = adsType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

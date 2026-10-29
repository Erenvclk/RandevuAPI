package com.example.randevu.mapper;

import org.springframework.stereotype.Component;


import com.example.randevu.dto.HizmetRequest;
import com.example.randevu.dto.HizmetResponse;
import com.example.randevu.entity.Hizmet;

@Component 

public class HizmetMapper {
   public Hizmet toEntity(HizmetRequest request){
         Hizmet hizmet = new Hizmet();
         hizmet.setAd(request.ad());
         hizmet.setSureDk(request.sureDk());
         hizmet.setFiyat(request.fiyat());

    return hizmet;

   }

   public HizmetResponse toResponse(Hizmet hizmet){
    return new HizmetResponse(
        hizmet.getId(),
        hizmet.getAd(),
        hizmet.getSureDk(),
        hizmet.getFiyat()
    );
   }
}

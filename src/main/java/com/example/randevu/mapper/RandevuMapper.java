package com.example.randevu.mapper;

import org.springframework.stereotype.Component;
import com.example.randevu.dto.RandevuResponse;
import com.example.randevu.entity.Randevu;

@Component
public class RandevuMapper {
    public RandevuResponse toResponse(Randevu randevu) {
        return new RandevuResponse(
                randevu.getId(),
                randevu.getMusteri().getId(),
                randevu.getMusteri().getAd() + " " + randevu.getMusteri().getSoyad(),
                randevu.getHizmet().getAd(),
                randevu.getBaslangic(),
                randevu.getBitis(),
                randevu.getDurum());
    }
}

package com.example.randevu.dto;

import com.example.randevu.enums.RandevuDurumu;
import java.time.LocalDateTime;

public record RandevuResponse(
        Long id,
        Long musteriId,
        String musteriAdSoyad,
        String hizmetAd,
        LocalDateTime baslangic,
        LocalDateTime bitis,
        RandevuDurumu durum) {

}

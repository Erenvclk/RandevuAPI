package com.example.randevu.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.randevu.entity.Hizmet;
import com.example.randevu.entity.Musteri;
import com.example.randevu.entity.Randevu;
import com.example.randevu.enums.RandevuDurumu;
import com.example.randevu.exception.CakismaException;
import com.example.randevu.repository.RandevuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RandevuService {
    private final RandevuRepository randevuRepository;
    private final MusteriService musteriService;
    private final HizmetService hizmetService;

    public Randevu kaydet(Long musteriId, Long hizmetId, LocalDateTime baslangic) {
        Musteri musteri = musteriService.getir(musteriId);
        Hizmet hizmet = hizmetService.getir(hizmetId);
        LocalDateTime bitis = baslangic.plusMinutes(hizmet.getSureDk());
        if (randevuRepository.existsByBaslangicLessThanAndBitisGreaterThanAndDurumNot(
                bitis, baslangic, RandevuDurumu.IPTAL)) {
            throw new CakismaException("Bu saat aralığında başka bir randevu var");
        }
        Randevu randevu = new Randevu();
        randevu.setMusteri(musteri);
        randevu.setHizmet(hizmet);
        randevu.setBaslangic(baslangic);
        randevu.setBitis(bitis);
        randevu.setDurum(RandevuDurumu.BEKLEMEDE);
        return randevuRepository.save(randevu);
    }
}

package com.example.randevu.service;

import org.springframework.stereotype.Service;

import com.example.randevu.entity.Hizmet;
import com.example.randevu.exception.KayitBulunamadiException;
import com.example.randevu.repository.HizmetRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HizmetService {
    private final HizmetRepository hizmetRepository;

    public Hizmet getir(Long id) {
        return hizmetRepository.findById(id)
                .orElseThrow(() -> new KayitBulunamadiException("hizmet bulunamadı"));
    }

    public List<Hizmet> listele() {
        return hizmetRepository.findAll();
    }

    public Hizmet kaydet(Hizmet hizmet) {
        return hizmetRepository.save(hizmet);
    }

    public void sil(Long id) {
        Hizmet hizmet = getir(id);
        hizmetRepository.delete(hizmet);
    }

    public Hizmet guncelle(Long id, Hizmet hizmet) {
        Hizmet mevcuHizmet = getir(id);
        mevcuHizmet.setAd(hizmet.getAd());
        mevcuHizmet.setSureDk(hizmet.getSureDk());
        mevcuHizmet.setFiyat(hizmet.getFiyat());
        return hizmetRepository.save(mevcuHizmet);
    }
}

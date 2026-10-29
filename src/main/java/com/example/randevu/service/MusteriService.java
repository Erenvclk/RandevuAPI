package com.example.randevu.service;

import org.springframework.stereotype.Service;

import com.example.randevu.entity.Musteri;
import com.example.randevu.repository.MusteriRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusteriService {
    private final MusteriRepository musteriRepository;

    public Musteri kaydet(Musteri musteri) {

        if (musteriRepository.existsByEmail(musteri.getEmail())) {
            throw new RuntimeException("bu e-posta zaten kayıtlı");

        }
        return musteriRepository.save(musteri);
    }

    public List<Musteri> adListele(String ad) {

        List<Musteri> musteriAdlari = musteriRepository.findByAd(ad);

        if (musteriAdlari.isEmpty()) {
            throw new RuntimeException("bu isimde musteri yok");

        }
        return musteriAdlari;
    }

    public Musteri getir(Long id) {
        return musteriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı"));

    }

    public List<Musteri> listele() {
        return musteriRepository.findAll();
    }
}

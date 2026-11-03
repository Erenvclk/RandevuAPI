package com.example.randevu.service;

import org.springframework.stereotype.Service;

import com.example.randevu.entity.Musteri;
import com.example.randevu.exception.CakismaException;
import com.example.randevu.exception.KayitBulunamadiException;
import com.example.randevu.repository.MusteriRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusteriService {
    private final MusteriRepository musteriRepository;

    public Musteri kaydet(Musteri musteri) {

        if (musteriRepository.existsByEmail(musteri.getEmail())) {
            throw new CakismaException("bu eposta zaten kayıtlı");

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
                .orElseThrow(() -> new KayitBulunamadiException("Musteri bulunamadı"));

    }

    public List<Musteri> listele() {
        return musteriRepository.findAll();
    }

    public void sil(Long id) {
        Musteri musteri = getir(id);
        musteriRepository.delete(musteri);
    }

    public Musteri guncelle(Long id, Musteri musteri) {
        Musteri mevcutMusteri = getir(id);
        if (!mevcutMusteri.getEmail().equals(musteri.getEmail())
                && musteriRepository.existsByEmail(musteri.getEmail())) {
            throw new CakismaException("Bu e-posta zaten kayıtlı");
        }
        mevcutMusteri.setAd(musteri.getAd());
        mevcutMusteri.setSoyad(musteri.getSoyad());
        mevcutMusteri.setTelefon(musteri.getTelefon());
        mevcutMusteri.setEmail(musteri.getEmail());
        return musteriRepository.save(mevcutMusteri);
    }
}

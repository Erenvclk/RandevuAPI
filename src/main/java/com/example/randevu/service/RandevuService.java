package com.example.randevu.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.randevu.entity.Hizmet;
import com.example.randevu.entity.Musteri;
import com.example.randevu.entity.Randevu;
import com.example.randevu.enums.RandevuDurumu;
import com.example.randevu.exception.CakismaException;

import com.example.randevu.repository.RandevuRepository;
import com.example.randevu.exception.KayitBulunamadiException;
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

    public Randevu getir(Long id) {
        Randevu randevu = randevuRepository.findById(id)
                .orElseThrow(() -> new KayitBulunamadiException("Randevu bulunamadı"));
        return randevu;
    }

    public List<Randevu> listele() {
        return randevuRepository.findAll();

    }

    public void sil(Long id) {
        Randevu randevu = getir(id);
        randevuRepository.delete(randevu);
    }

    // public Randevu güncelle(long id, Long musteriId, Long hizmetId, LocalDateTime baslangic) {
    //     Randevu mevcutRandevu = getir(id);
    //     if (randevuRepository.existsByBaslangicLessThanAndBitisGreaterThanAndDurumNot(baslangic, baslangic,
    //             RandevuDurumu.IPTAL)) {
    //         throw new CakismaException("Bu randevu iptal edilmiş");
    //     }
    //     Musteri müşteri = musteriService.getir(musteriId);
    //     Hizmet hizmet = hizmetService.getir(hizmetId);
    //     LocalDateTime bitis = baslangic.plusMinutes(hizmet.getSureDk());

    //     if (randevuRepository.existsByIdNotAndBaslangicLessThanAndBitisGreaterThanAndDurumNot(hizmetId, bitis,
    //             baslangic, RandevuDurumu.IPTAL)) {
    //         mevcutRandevu.setMusteri(müşteri);
    //         mevcutRandevu.setHizmet(hizmet);
    //         mevcutRandevu.setBaslangic(baslangic);
    //         mevcutRandevu.setBitis(bitis);
    //         return randevuRepository.save(mevcutRandevu);
    //     }
    //     return mevcutRandevu;
    // }

    public Randevu guncelle(Long id, Long musteriId, Long hizmetId, LocalDateTime baslangic) {
        Randevu mevcut = getir(id);

        if (mevcut.getDurum() == RandevuDurumu.IPTAL) {
            throw new CakismaException("İptal edilmiş randevu güncellenemez");
        }

        Musteri musteri = musteriService.getir(musteriId);
        Hizmet hizmet = hizmetService.getir(hizmetId);
        LocalDateTime bitis = baslangic.plusMinutes(hizmet.getSureDk());

        if (randevuRepository.existsByIdNotAndBaslangicLessThanAndBitisGreaterThanAndDurumNot(
                id, bitis, baslangic, RandevuDurumu.IPTAL)) {
            throw new CakismaException("Bu saat aralığında başka bir randevu var");
        }

        mevcut.setMusteri(musteri);
        mevcut.setHizmet(hizmet);
        mevcut.setBaslangic(baslangic);
        mevcut.setBitis(bitis);
        return randevuRepository.save(mevcut);
    }

    public Randevu iptalEt(long id) {
        Randevu randevu = getir(id);

        if (randevu.getDurum() == RandevuDurumu.IPTAL) {
            throw new CakismaException("Randevu zaten iptal edilmiş");
        }
        randevu.setDurum(RandevuDurumu.IPTAL);
        return randevuRepository.save(randevu);
    }
}

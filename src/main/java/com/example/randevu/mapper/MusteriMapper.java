package com.example.randevu.mapper;

import org.springframework.stereotype.Component;
import com.example.randevu.dto.MusteriRequest;
import com.example.randevu.dto.MusteriResponse;
import com.example.randevu.entity.Musteri;

@Component // bu sınıf bean yap anlamı tasır. @service ve @controller ozelleşmiş
           // halleridir.
public class MusteriMapper {
    public Musteri toEntity(MusteriRequest request) { // MusteriRequest -> DTO , requst ->entity
                                                      // dto girer, entity çıkar.
                                                      // dto daki veriler entity e aktarılır.
        Musteri musteri = new Musteri();// burada ürettiğin entity nesnesi bean olmadıgı için nesnesi otomatik
                                        // üretilmiyor. sen new ile üretiyorsun.

        musteri.setAd(request.ad()); // musteri entitysinin ad değerini request deki ad ile eşitliyoruz.
        musteri.setSoyad(request.soyad());
        musteri.setTelefon(request.telefon());
        musteri.setEmail(request.email());
        return musteri;
    }

    public MusteriResponse toResponse(Musteri musteri) {// entity girer DTo çıkar.

        // toResponse da
        /*
         * yeni bir response nesnesi üret,
         * içine dtodaki sırayla sunları koy,
         * don.
         * diyorsun.
         * 
         * burada aslında
         * MusteriResponse response = new Musteriresponse(
         * musteri.getID(), demek yerine
         * return new MusteriResponse diyorsun.
         * )
         */
        return new MusteriResponse( // bos bir MusteriResponse nesnesi üret ve içini sunlarla doldur, don.
                musteri.getId(),
                musteri.getAd(),
                musteri.getSoyad(),
                musteri.getTelefon(),
                musteri.getEmail()

        );
    }
}

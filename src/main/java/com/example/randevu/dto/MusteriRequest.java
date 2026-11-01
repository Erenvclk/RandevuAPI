package com.example.randevu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//request dısarıdan alınan verileri tutyor. 
// recordun ozelliği:
/*
    sadece veri tutmak için bir yapı.
    lombok olmadan kendı getter setterlarını ve bos constrıctounu yazıyor.

    ** getterları getAd() gibi degıl ad() gibi yazıyor.
    setter yok.
*/
public record MusteriRequest(
        @NotBlank(message = "İsminiz boş olamaz") String ad,
        @NotBlank(message = "Soy isminiz boş olamaz") String soyad,
        @NotBlank(message = "İletişim için telefon numaranızı giriniz") String telefon,
        @NotBlank(message = "Lütfen mail adresinizi giriniz") 
        @Email (message = "Lütfen geçerli bir e-mail giriniz.") String email) {

}

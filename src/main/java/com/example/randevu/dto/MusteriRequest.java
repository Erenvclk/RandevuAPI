package com.example.randevu.dto;
//request dısarıdan alınan verileri tutyor. 
// recordun ozelliği:
/*
    sadece veri tutmak için bir yapı.
    lombok olmadan kendı getter setterlarını ve bos constrıctounu yazıyor.

    ** getterları getAd() gibi degıl ad() gibi yazıyor.
    setter yok.
*/
public record MusteriRequest(
    String ad,
    String soyad,
    String telefon,
    String email
) {
    
}

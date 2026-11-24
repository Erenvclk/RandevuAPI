package com.example.randevu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record KayitRequest(
        @NotBlank (message = "Ad boş olamaz")String ad,
        @NotBlank (message = "Soy ad boş olamaz")String soyad,
        @NotBlank (message = "Telefon numarı giriniz")String telefon,
        @NotBlank (message = "Email alanı boş olamaz")@Email String email,
        @NotBlank (message = "Şifre alanı boş olamaz")@Size(min = 8, message = "Şifre en az 8 karakter olmalıdır.") String sifre) {

}

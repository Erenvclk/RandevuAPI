package com.example.randevu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GirisRequest(
        @NotBlank(message = "Lütfen mail adresinizi giriniz") @Email(message = "Lütfen geçerli bir e-mail giriniz.") String email,  
        @NotBlank (message = "Lütfen şifrenizi giriniz") @Size (min = 8, message = "Şifre en az 8 karakter olmalıdır.") String sifre)   
       {

}

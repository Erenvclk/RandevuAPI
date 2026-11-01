package com.example.randevu.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HizmetRequest(
        @NotBlank(message = "Hizmet adı boş olamaz") String ad,
        @NotNull(message = "Süre boş olamaz") @Positive(message = "Süre sıfırdan büyük olmalı") Integer sureDk,
        @NotNull(message = "Fiyat boş olamaz") @Positive(message = "Fiyat sıfırdan büyük olmalı") BigDecimal fiyat) {
}

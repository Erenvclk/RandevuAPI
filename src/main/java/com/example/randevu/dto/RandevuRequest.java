package com.example.randevu.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record RandevuRequest(
        @NotNull(message = "Müşteri id bilgisi boş olamaz.") Long musteriId,
        @NotNull(message = "Hizmet id bilgisi boş olamaz") Long hizmetId,
        @NotNull(message = "Randevu baslangıc saatini girmelisiniz") @Future(message = "Randevu tarihi gelecekte olmalı ") LocalDateTime baslangic) {

}

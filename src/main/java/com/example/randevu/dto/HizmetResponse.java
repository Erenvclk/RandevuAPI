package com.example.randevu.dto;

import java.math.BigDecimal;

public record HizmetResponse(
        Long id,
        String ad,
        Integer sureDk,
        BigDecimal fiyat) {

}

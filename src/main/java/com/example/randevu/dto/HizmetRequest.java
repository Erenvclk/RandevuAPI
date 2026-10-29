package com.example.randevu.dto;

import java.math.BigDecimal;

public record HizmetRequest(

        String ad,
        Integer sureDk,
        BigDecimal fiyat) {

}

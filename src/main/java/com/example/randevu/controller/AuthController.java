package com.example.randevu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.randevu.dto.GirisRequest;
import com.example.randevu.dto.GirisResponse;
import com.example.randevu.dto.KayitRequest;
import com.example.randevu.dto.MusteriResponse;
import com.example.randevu.entity.Musteri;
import com.example.randevu.mapper.MusteriMapper;
import com.example.randevu.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MusteriMapper musteriMapper;

    @PostMapping("/kayit")
    public MusteriResponse kayit(@Valid @RequestBody KayitRequest request) {
        Musteri musteri = authService.kayit(
                request.ad(), request.soyad(), request.telefon(), request.email(), request.sifre());
        return musteriMapper.toResponse(musteri);
    }

    @PostMapping("/giris")
    public GirisResponse giris(@Valid @RequestBody GirisRequest request) {
        String token = authService.giris(request.email(), request.sifre());
        return new GirisResponse(token);
    }
}
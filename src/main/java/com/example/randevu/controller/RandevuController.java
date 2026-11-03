package com.example.randevu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.randevu.dto.RandevuRequest;
import com.example.randevu.dto.RandevuResponse;
import com.example.randevu.entity.Randevu;
import com.example.randevu.mapper.RandevuMapper;
import com.example.randevu.service.RandevuService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/randevular")
@RequiredArgsConstructor

public class RandevuController {
    private final RandevuService randevuService;
    private final RandevuMapper randevuMapper;

    @PostMapping
    public RandevuResponse kaydet(@Valid @RequestBody RandevuRequest request) {
        Randevu randevu = randevuService.kaydet(
                request.musteriId(),
                request.hizmetId(),
                request.baslangic());
        return randevuMapper.toResponse(randevu);
    }
}

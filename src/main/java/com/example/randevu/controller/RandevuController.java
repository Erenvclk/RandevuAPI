package com.example.randevu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import java.util.List;

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

    @GetMapping("/{id}")
    public RandevuResponse getir(@PathVariable Long id) {
        Randevu randevu = randevuService.getir(id);
        return randevuMapper.toResponse(randevu);
    }

    @GetMapping
    public List<RandevuResponse> listele() {
        List<Randevu> randevular = randevuService.listele();
        return randevular.stream()
                .map(randevuMapper::toResponse)
                .toList();
    }

    @PatchMapping("/{id}/iptal")
    public RandevuResponse iptalEt(@PathVariable Long id) {
        Randevu randevu = randevuService.iptalEt(id);
        return randevuMapper.toResponse(randevu);
    }

    @PutMapping ("/{id}")
    public RandevuResponse guncelle(@PathVariable Long id, @Valid @RequestBody RandevuRequest request) {
        Randevu randevu = randevuService.guncelle(
                id,
                request.musteriId(),
                request.hizmetId(),
                request.baslangic());
        return randevuMapper.toResponse(randevu);
    }
}
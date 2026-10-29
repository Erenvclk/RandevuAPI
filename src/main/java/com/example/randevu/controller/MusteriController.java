package com.example.randevu.controller;

import com.example.randevu.service.MusteriService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.randevu.dto.MusteriRequest;
import com.example.randevu.dto.MusteriResponse;
import com.example.randevu.entity.Musteri;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import com.example.randevu.mapper.MusteriMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/musteriler")
@RequiredArgsConstructor

public class MusteriController {

    private final MusteriService musteriService;
    private final MusteriMapper musteriMapper;

    // @PostMapping
    // public Musteri kaydet(@RequestBody Musteri musteri) {
    // return musteriService.kaydet(musteri);
    // }
    @PostMapping
    public MusteriResponse kaydet(@RequestBody MusteriRequest request) {
        Musteri musteri = musteriMapper.toEntity(request);
        Musteri kayitli = musteriService.kaydet(musteri);
        return musteriMapper.toResponse(kayitli);

    }

    // @GetMapping("/{id}")
    // public Musteri getir(@PathVariable Long id) {
    // return musteriService.getir(id);
    // }

    @GetMapping("/{id}")
    public MusteriResponse getir(@PathVariable Long id) {

        Musteri musteri = musteriService.getir(id);
        return musteriMapper.toResponse(musteri);
    }

    // @GetMapping
    // public List<Musteri> musteriler() {
    // return musteriService.musteriler(new Musteri());
    // }
    // }
    @GetMapping
    public List<MusteriResponse> listele() {
        List<Musteri> musteriler = musteriService.listele();
        List<MusteriResponse> cevap = new ArrayList<>();

        for (Musteri m : musteriler) {
            cevap.add(musteriMapper.toResponse(m));

        }
        return cevap;
    }
}
package com.example.randevu.controller;

import com.example.randevu.service.MusteriService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.randevu.entity.Musteri;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/musteriler")
@RequiredArgsConstructor

public class MusteriController {

    private final MusteriService musteriService;

    @PostMapping
    public Musteri kaydet(@RequestBody Musteri musteri) {
        return musteriService.kaydet(musteri);
    }

    @GetMapping("/{id}")
    public Musteri getir(@PathVariable Long id) {
        return musteriService.getir(id);
    }

    @GetMapping
    public List<Musteri> musteriler() {
        return musteriService.musteriler(new Musteri());
    }
}
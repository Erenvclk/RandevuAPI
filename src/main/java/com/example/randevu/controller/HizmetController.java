package com.example.randevu.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.example.randevu.service.HizmetService;
import com.example.randevu.entity.Hizmet;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/hizmetler")
@RequiredArgsConstructor
// kaydet , getir , listele
public class HizmetController {
    private final HizmetService hizmetService;

    @PostMapping
    public Hizmet kaydet(@RequestBody Hizmet hizmet) {
        return hizmetService.kaydet(hizmet);
    }

    @GetMapping("/{id}")
    public Hizmet getir(@PathVariable Long id) {
        return hizmetService.getir(id);
    }

    @GetMapping
    public List<Hizmet> listele() {
        return hizmetService.listele();
    }
}

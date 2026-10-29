package com.example.randevu.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.example.randevu.service.HizmetService;

import com.example.randevu.dto.HizmetRequest;
import com.example.randevu.dto.HizmetResponse;

import com.example.randevu.entity.Hizmet;

import com.example.randevu.mapper.HizmetMapper;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/hizmetler")
@RequiredArgsConstructor
// kaydet , getir , listele
public class HizmetController {
    private final HizmetService hizmetService;
    private final HizmetMapper hizmetMapper;

    // @PostMapping
    // public Hizmet kaydet(@RequestBody Hizmet hizmet) {
    // return hizmetService.kaydet(hizmet);
    // }
    @PostMapping
    public HizmetResponse kaydet(@RequestBody HizmetRequest request) {
        Hizmet hizmet = hizmetMapper.toEntity(request);
        Hizmet kayitli = hizmetService.kaydet(hizmet);
        return hizmetMapper.toResponse(kayitli);

    }

    // @GetMapping("/{id}")
    // public Hizmet getir(@PathVariable Long id) {
    // return hizmetService.getir(id);
    // }
    @GetMapping("/{id}")
    public HizmetResponse getir(@PathVariable Long id) {
        Hizmet hizmet = hizmetService.getir(id);
        return hizmetMapper.toResponse(hizmet);
    }

    // @GetMapping
    // public List<Hizmet> listele() {
    // return hizmetService.listele();
    // }
    @GetMapping
    public List<HizmetResponse> listele() {
        List<Hizmet> hizmetler = hizmetService.listele();
        List<HizmetResponse> cevap = new ArrayList<>();

        for (Hizmet h : hizmetler) {
            cevap.add(hizmetMapper.toResponse(h));

        }
        return cevap;
    }

}

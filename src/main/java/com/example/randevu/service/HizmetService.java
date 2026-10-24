package com.example.randevu.service;

import org.springframework.stereotype.Service;

import com.example.randevu.entity.Hizmet;
import com.example.randevu.repository.HizmetRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HizmetService {
    private final HizmetRepository hizmetRepository;

    public Hizmet getir(Long id) {
        return hizmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("hizmet bulunamadı"));
    }

    public List<Hizmet> listele() {
        return hizmetRepository.findAll();
    }

    public Hizmet kaydet(Hizmet hizmet) {
        return hizmetRepository.save(hizmet);
    }
}

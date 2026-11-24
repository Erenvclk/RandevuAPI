package com.example.randevu.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.randevu.entity.Musteri;
import com.example.randevu.enums.Rol;
import com.example.randevu.exception.CakismaException;
import com.example.randevu.exception.GirisHatasiException;
import com.example.randevu.repository.MusteriRepository;


import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    private final MusteriRepository musteriRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

        public Musteri kayit(String ad, String soyad, String telefon, String email, String sifre ){
            if (musteriRepository.existsByEmail(email)){
                throw new CakismaException("Bu e-posta zaten kayıtlı");
            }
            Musteri musteri = new Musteri();
            musteri.setAd(ad);
            musteri.setSoyad(soyad);
            musteri.setTelefon(telefon);
            musteri.setEmail(email);
            musteri.setSifre(passwordEncoder.encode(sifre));
            musteri.setRol(Rol.MUSTERI);
            return musteriRepository.save(musteri);
        }

        public  String giris(String email, String sifre){
            Musteri musteri = musteriRepository.findByEmail(email)
                .orElseThrow(() -> new GirisHatasiException("E-posta veya şifre hatalı"));
             
            if(!passwordEncoder.matches(sifre, musteri.getSifre())){
                throw new GirisHatasiException("E-posta veya şifre hatalı");
            }

            return jwtService.tokenUret(musteri.getEmail(), musteri.getRol());
        }
        
}

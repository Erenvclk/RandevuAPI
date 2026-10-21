package com.example.randevu.entity ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 
import jakarta.persistence.GenerationType;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 

public class Musteri {
   
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ad;
    
    @Column(nullable = false)
    private String soyad;
   
    @Column(nullable = false)
    private String telefon;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    
}
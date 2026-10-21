package com.example.randevu.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;
import com.example.randevu.enums.RandevuDurumu;
import jakarta.persistence.EnumType;

@Entity
@Getter 
@Setter
@NoArgsConstructor 

public class Randevu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musteri_id", nullable = false)
    private Musteri musteri;

    @ManyToOne
    @JoinColumn(name = "hizmet_id", nullable = false)
    private Hizmet hizmet;

    @Column(nullable = false)
    private LocalDateTime baslangic;

    @Column(nullable = false)
    private LocalDateTime bitis;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING )
    private RandevuDurumu durum;
    
   
}                              
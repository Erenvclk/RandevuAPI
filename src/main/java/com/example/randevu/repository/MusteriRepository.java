package com.example.randevu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.randevu.entity.Musteri;
import java.util.Optional;
import java.util.List;

public interface MusteriRepository extends JpaRepository<Musteri, Long> {

    Optional<Musteri> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Musteri> findByAd(String ad);
}

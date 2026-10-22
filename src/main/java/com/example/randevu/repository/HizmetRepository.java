package com.example.randevu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.randevu.entity.Hizmet;
import java.util.List;

public interface HizmetRepository extends JpaRepository<Hizmet, Long> {

    List<Hizmet> findByAd(String ad);
}

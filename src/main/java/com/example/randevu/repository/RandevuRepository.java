package com.example.randevu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.randevu.entity.Randevu;

public interface RandevuRepository extends JpaRepository<Randevu, Long> {

}

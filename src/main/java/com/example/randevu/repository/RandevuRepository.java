package com.example.randevu.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.randevu.entity.Randevu;
import com.example.randevu.enums.RandevuDurumu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RandevuRepository extends JpaRepository<Randevu, Long> {
        boolean existsByBaslangicLessThanAndBitisGreaterThanAndDurumNot(
                        LocalDateTime yeniBitis,
                        LocalDateTime yeniBaslangic,
                        RandevuDurumu durum);

        boolean existsByIdNotAndBaslangicLessThanAndBitisGreaterThanAndDurumNot(
                        Long id,
                        LocalDateTime yeniBitis,
                        LocalDateTime yeniBaslangic,
                        RandevuDurumu durum);

        Page<Randevu> findByMusteriId(Long musteriId, Pageable pageable);
}

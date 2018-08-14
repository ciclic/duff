package com.gustavo.cervejaciclic.repository;

import com.gustavo.cervejaciclic.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CervejaRepository extends JpaRepository<Cerveja, Long> {
}

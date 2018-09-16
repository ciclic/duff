package com.duff.api.repository;

import com.duff.api.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, String> {
}

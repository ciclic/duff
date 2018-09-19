package com.duff.api.repository;

import com.duff.api.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, String> {

    List<Beer> findAllByMinTemperatureLessThanEqualAndMaxTemperatureGreaterThanEqual(double minTemperature, double maxTemperature);
}

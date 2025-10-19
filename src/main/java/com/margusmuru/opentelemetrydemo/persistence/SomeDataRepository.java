package com.margusmuru.opentelemetrydemo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SomeDataRepository extends JpaRepository<SomeDataEntity, Integer> {
}

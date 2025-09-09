package com.mederp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mederp.entities.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {

}

package com.codev.locatrash.repository;

import com.codev.locatrash.entity.Poubelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoubelleRepository extends JpaRepository<Poubelle, Long> {
}

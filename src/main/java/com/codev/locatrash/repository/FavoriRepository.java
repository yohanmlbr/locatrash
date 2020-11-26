package com.codev.locatrash.repository;

import com.codev.locatrash.entity.Favori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriRepository extends JpaRepository<Favori, Long> {

    List<Favori> findByUserId(long id);
}

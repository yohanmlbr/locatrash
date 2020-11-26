package com.codev.locatrash.repository;

import com.codev.locatrash.entity.Trash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrashRepository extends JpaRepository<Trash, String> {

    @Query("SELECT t.commune,COUNT(*) FROM Trash t GROUP BY commune")
    public List<Object[]> countTrashesByCommune();

    @Query(value ="SELECT *, ( acos(sin(RADIANS(latitude))*sin(RADIANS(?1))+cos(RADIANS(latitude))*cos(RADIANS(?1))*cos(RADIANS(longitude-?2)))*6371 ) AS distance FROM poubelle ORDER BY distance",nativeQuery = true)
    List<Trash> findByLatLon(double lat, double lon);
}

package org.juang.test.springboot.app.repository;

import org.juang.test.springboot.app.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select o from Owner o where o.name=?1")
    Optional<Owner> findByName(String name);
}


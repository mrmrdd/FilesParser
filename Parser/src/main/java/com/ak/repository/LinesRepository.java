package com.ak.repository;


import com.ak.entity.Lines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinesRepository extends JpaRepository<Lines, Long> {
}

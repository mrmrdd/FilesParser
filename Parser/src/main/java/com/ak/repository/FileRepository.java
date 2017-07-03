package com.ak.repository;


import com.ak.entity.SourceFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<SourceFiles, Long> {
}

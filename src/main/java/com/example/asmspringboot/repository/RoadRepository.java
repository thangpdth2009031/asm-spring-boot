package com.example.asmspringboot.repository;

import com.example.asmspringboot.entity.Road;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends JpaRepository<Road, Integer>, JpaSpecificationExecutor<Road> {

}

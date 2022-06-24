package com.example.asmspringboot.service;

import com.example.asmspringboot.entity.District;
import com.example.asmspringboot.entity.Road;
import com.example.asmspringboot.exception.NotFoundException;
import com.example.asmspringboot.repository.DistrictRepository;
import com.example.asmspringboot.repository.RoadRepository;
import com.example.asmspringboot.specification.RoadSpecification;
import com.example.asmspringboot.specification.SearchBody;
import com.example.asmspringboot.specification.SearchCriteria;
import com.example.asmspringboot.specification.SearchCriteriaOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoadService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RoadRepository roadRepository;
    public ResponseEntity<List<Road>> findAllAndFilter(SearchBody searchBody){
        Specification specification = Specification.where(null);
        if (searchBody.getRoadName() != null && searchBody.getRoadName().length() > 0){
            specification = specification.and(new RoadSpecification(new SearchCriteria("name", "=", searchBody.getRoadName())));
        }
        if (searchBody.getDistrictId() > 0){
            specification = specification.and(new RoadSpecification(new SearchCriteria("district_id", "=", searchBody.getDistrictId())));
        }
        return (ResponseEntity<List<Road>>) roadRepository.findAll(specification);
    }
    public Road save(Road road) {
        District district = districtRepository.findById(road.getDistrict_id()).orElse(null);
        if (district == null) {
            throw new NotFoundException("No district found!");
        }
        else {
            road.setDistrict(district);
            roadRepository.save(road);
        }
        return road;
    }
}

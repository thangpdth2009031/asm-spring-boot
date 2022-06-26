package com.example.asmspringboot.service;

import com.example.asmspringboot.entity.District;
import com.example.asmspringboot.entity.Road;
import com.example.asmspringboot.exception.NotFoundException;
import com.example.asmspringboot.repository.DistrictRepository;
import com.example.asmspringboot.repository.RoadRepository;
import com.example.asmspringboot.specification.RoadSpecification;
import com.example.asmspringboot.specification.SearchBody;
import com.example.asmspringboot.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RoadRepository roadRepository;
    public List<Road> findAllAndFilter(SearchBody searchBody){
        Specification specification = Specification.where(null);
        if (searchBody.getRoadName() != null && searchBody.getRoadName().length() > 0){
            specification = specification.and(new RoadSpecification(new SearchCriteria("name", "=", searchBody.getRoadName())));
        }
        if (searchBody.getStatus() != 2){
            specification = specification.and(new RoadSpecification(new SearchCriteria("status", "=", searchBody.getStatus())));
        }
        if (searchBody.getDistrictId() != -1){
            specification = specification.and(new RoadSpecification(new SearchCriteria("district_id", "=", searchBody.getDistrictId())));
        }
        List<Road> roads = roadRepository.findAll(specification);
        return roads;
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

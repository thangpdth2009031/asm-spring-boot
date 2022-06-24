package com.example.asmspringboot.service;

import com.example.asmspringboot.entity.District;
import com.example.asmspringboot.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    public List<District> findAll() {
        return districtRepository.findAll();
    }

    public District save(District district) {
        return districtRepository.save(district);
    }
}

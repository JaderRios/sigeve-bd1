package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Region;
import com.example.sigeve.SIGEVE.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public Page<Region> list(Pageable pageable) {
        return regionRepository.list(pageable);
    }

    public Region getById(String id) {
        return regionRepository.getById(id);
    }

    public Region create(Region region) {
        region.setId(generateRegionId());
        return regionRepository.create(region);
    }

    public Region update(String id, Region regionDetails) {
        return regionRepository.update(id, regionDetails);
    }

    public boolean delete(String id) {
        return regionRepository.delete(id);
    }

    private String generateRegionId() {
        long nextId = regionRepository.count() + 1;
        return Long.toString(nextId);
    }
}

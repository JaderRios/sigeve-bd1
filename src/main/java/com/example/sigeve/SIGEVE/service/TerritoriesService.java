package com.example.sigeve.SIGEVE.service;

import com.example.sigeve.SIGEVE.model.Territories;
import com.example.sigeve.SIGEVE.repository.TerritoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TerritoriesService {

    @Autowired
    private TerritoriesRepository territoriesRepository;

    public Page<Territories> list(Pageable pageable) {
        return territoriesRepository.list(pageable);
    }

    public Territories getById(String id) {
        try {
            return territoriesRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Territories create(Territories territory) {
        territory.setId(generateTerritoryId());
        return territoriesRepository.create(territory);
    }

    public Territories update(String id, Territories territoryDetails) {
        return territoriesRepository.update(id, territoryDetails);
    }

    public boolean delete(String id) {
        return territoriesRepository.delete(id);
    }

    private String generateTerritoryId() {
        long count = territoriesRepository.count() + 1;
        return String.format("T%04d", count);
    }
}

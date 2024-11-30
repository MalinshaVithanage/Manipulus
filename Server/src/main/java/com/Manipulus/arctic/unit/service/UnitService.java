package com.Manipulus.arctic.unit.service;

import com.Manipulus.arctic.unit.exception.UnitNotFoundException;
import com.Manipulus.arctic.unit.model.Unit;
import com.Manipulus.arctic.unit.repository.IUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final IUnitRepository unitRepository;


    @Autowired
    public UnitService(IUnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit addUnit(Unit unit){
        return unitRepository.save(unit);
    }

    public List<Unit> getUnits(){
        return unitRepository.findAll();
    }
    public Unit updateUnit(int id, Unit unit){
        return unitRepository.save(unit);
    }
    public Unit findUnitById(int id){
        return unitRepository.findById(id)
                .orElseThrow(()-> new UnitNotFoundException(" unit by id"+ id + "was not found"));
    }

    @Transactional
    public void deleteUnitById(int id){
        Optional<Unit> existingUnit = unitRepository.findById(id);
        if (existingUnit != null) {
            unitRepository.deleteById(id);
        }

    }




}

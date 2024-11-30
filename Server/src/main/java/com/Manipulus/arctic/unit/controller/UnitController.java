package com.Manipulus.arctic.unit.controller;

import com.Manipulus.arctic.unit.model.Unit;
import com.Manipulus.arctic.unit.service.UnitService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
//@CrossOrigin("*")
public class UnitController {
    private static UnitService unitService;
    UnitController(UnitService unitService){
        this.unitService = unitService;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public Unit addUnit(@RequestBody Unit unit) {
        return unitService.addUnit(unit);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<Unit> getUnits() {
        return unitService.getUnits();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Unit getUnitById(@PathVariable("id") int id) {
        return unitService.findUnitById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public Unit updateUnit(@PathVariable("id") int id, @RequestBody Unit unit) {
        return unitService.updateUnit(id, unit);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteUnitById(@PathVariable("id") int id) {
        unitService.deleteUnitById(id);
    }
}

package com.Manipulus.arctic.siteVisit.controller;

import com.Manipulus.arctic.siteVisit.dto.VehicleOrderRequest;
import com.Manipulus.arctic.siteVisit.repo.SiteVisitRepo;
import com.Manipulus.arctic.siteVisit.siteVisit.SiteVisit;
import com.Manipulus.arctic.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class VehicleOrderController {
    @Autowired
    private SiteVisitRepo siteVisitRepo;
    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping("/placeVehicleOrder")
    public SiteVisit addSiteVisit(@RequestBody VehicleOrderRequest request){
        SiteVisit siteVisit = request.getSiteVisit();

        // Make sure the SiteVisit object is not null
        if (siteVisit == null) {
            throw new IllegalArgumentException("SiteVisit must not be null");
        }

        // Perform any necessary validations or modifications on the siteVisit object

        // Save the siteVisit entity
        return siteVisitRepo.save(siteVisit);
    }

    @GetMapping("/findAllVehicleOrders")
    public List<SiteVisit>findAllVehicleOrders(){
    return siteVisitRepo.findAll();
    }



}

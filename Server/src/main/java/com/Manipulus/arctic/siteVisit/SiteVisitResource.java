package com.Manipulus.arctic.siteVisit;

import com.Manipulus.arctic.siteVisit.dto.VehicleOrderRequest;
import com.Manipulus.arctic.siteVisit.service.SiteVisitService;
import com.Manipulus.arctic.siteVisit.siteVisit.SiteVisit;
import com.Manipulus.arctic.vehicle.model.Vehicle;
import com.Manipulus.arctic.vehicle.repository.VehicleRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/siteVisit")
public class SiteVisitResource {

    private SiteVisitService siteVisitService ;
    private VehicleRepository vehicleRepository;


    public SiteVisitResource(com.Manipulus.arctic.siteVisit.service.SiteVisitService SiteVisitService) {
        this.siteVisitService = SiteVisitService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<SiteVisit>> getAllSiteVisits(){
        List<SiteVisit>siteVisits=siteVisitService.findAllSiteVisits();

        return new ResponseEntity<>(siteVisits, HttpStatus.OK);
    }
    @GetMapping("/find/{siteVisitId}")
    public ResponseEntity<SiteVisit>getSiteVisitById(@PathVariable("siteVisitId") Long siteVisitId){
        SiteVisit siteVisit=siteVisitService.findSiteVisitBySiteVisitId(siteVisitId);
        return new ResponseEntity<>(siteVisit, HttpStatus.OK);

    }
    @PostMapping("/add")
    public ResponseEntity<SiteVisit>addSiteVisit(@RequestBody SiteVisit siteVisit){
        SiteVisit newSiteVisit= siteVisitService.addSiteVisit(siteVisit);
        return new ResponseEntity<>(newSiteVisit, HttpStatus.CREATED);
    }
    /*@PostMapping("/add")
    public ResponseEntity<SiteVisit> addSiteVisit(@RequestBody SiteVisit siteVisit) {
        // Save the site visit
        SiteVisit newSiteVisit = siteVisitService.addSiteVisit(siteVisit);

        // Associate vehicles with the site visit
        List<Vehicle> vehicles = siteVisit.getVehicles();
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                vehicle.setSiteVisit(newSiteVisit);
            }
            newSiteVisit.setVehicles(vehicles);
            vehicleRepository.saveAll(vehicles);
        }

        // Return the response with the newly created site visit
        return ResponseEntity.status(HttpStatus.CREATED).body(newSiteVisit);
    }
    */


    @PutMapping("/update")
    public ResponseEntity<SiteVisit>updateSiteVisit(@RequestBody SiteVisit siteVisit){
        SiteVisit updateSiteVisit=siteVisitService.updateSiteVisit(siteVisit);
        return new ResponseEntity<>(updateSiteVisit, HttpStatus.CREATED);

    }
    @DeleteMapping ("/delete/{siteVisitId}")
    public ResponseEntity<?> deleteSiteVisit(@PathVariable("siteVisitId")Long siteVisitId){
        siteVisitService.deleteSiteVisit(siteVisitId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


   /* public ResponseEntity<List<SiteVisit>> findAllVehicleOrders(){
        List<SiteVisit>siteVisits=siteVisitService.findAllSiteVisits();
        return new ResponseEntity<>(siteVisits, HttpStatus.OK);
    }*/

}


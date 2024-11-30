package com.Manipulus.arctic.vehicle;

import com.Manipulus.arctic.vehicle.model.Vehicle;
import com.Manipulus.arctic.vehicle.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private  VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicle() {
        List<Vehicle> vehicles = vehicleService.findAllVehicle();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id) {
        Vehicle vehicles = vehicleService.findVehicleById(id);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getVehiclePhoto(@PathVariable long id) {
        return vehicleService.getVehiclePhoto(id);
    }


    @PostMapping("/add")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);

    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> addPhotoToVehicle(@PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException {
        return vehicleService.addPhotoToVehicle(id,file);
    }

    @PutMapping("/update")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
        Vehicle updateVehicle = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(updateVehicle, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable("id") Long id) {
        vehicleService.deleteVehicleById(id);
    }

}

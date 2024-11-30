package com.Manipulus.arctic.vehicle.service;

import com.Manipulus.arctic.vehicle.exception.VehicleNotFoundException;
import com.Manipulus.arctic.vehicle.model.Vehicle;
import com.Manipulus.arctic.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {
    @Autowired
    private static com.Manipulus.arctic.vehicle.repository.VehicleRepository VehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.VehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setVehicle_code(UUID.randomUUID().toString());
        return VehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAllVehicle() {
        return VehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return VehicleRepository.save(vehicle);
    }

    public Vehicle findVehicleById(Long id) {
        return VehicleRepository.findVehicleById(id)
                .orElseThrow(() -> new VehicleNotFoundException(" vehicle by id" + id + "was not found"));
    }

    public ResponseEntity<byte[]> getVehiclePhoto(long id) {
        Vehicle vehicle = VehicleRepository.findVehicleById(id).orElseThrow(() -> new VehicleNotFoundException("Vehicle Does Not Exist with id: " + id));
        byte[] image = vehicle.getVehicle_image();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    public ResponseEntity<String> addPhotoToVehicle(long id,MultipartFile file) throws IOException {
        try {
            Vehicle vehicle = VehicleRepository.findVehicleById(id).orElseThrow(() -> new VehicleNotFoundException("Vehicle Does Not Exist with id: " + id));

            vehicle.setVehicle_image(file.getBytes());

            vehicle.setImageapi("http://localhost:8080/vehicle/" + id + "/photo");

            VehicleRepository.save(vehicle);

            return ResponseEntity.ok("Photo added successfully to Doctor with ID: " + id);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body("Error: Data Integrity Violation occurred. Please check your input.");
        }
    }

    @Transactional
    public void deleteVehicleById(long id) {
        VehicleRepository.deleteVehicleById(id);
    }


}

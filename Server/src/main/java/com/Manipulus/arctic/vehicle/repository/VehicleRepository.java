package com.Manipulus.arctic.vehicle.repository;

import com.Manipulus.arctic.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findVehicleById(Long id);

    void deleteVehicleById(long id);
}

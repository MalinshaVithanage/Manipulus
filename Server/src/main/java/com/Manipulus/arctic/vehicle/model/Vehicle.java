package com.Manipulus.arctic.vehicle.model;

import com.Manipulus.arctic.siteVisit.siteVisit.SiteVisit;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column(name = "vehicle_name")
    private String vehicle_name;

    @Column(name = "vehicle_number")
    private String vehicle_number;

    @Column(name = "number_of_passengers")
    private String number_of_passengers;

    @Column(name = "active_state")
    private String active_state;

    @Column(name = "vehicle_image", columnDefinition = "longblob")
    private byte[] vehicle_image;

    @Column(name = "ImageAPI")
    private String imageapi;

    @Column(name = "vehicle_code")
    private String vehicle_code;

    @ManyToOne
    @JoinColumn(name = "site_visit_id")
    private SiteVisit siteVisit;



    public String getVehicle_code() {
        return vehicle_code;
    }

    public void setVehicle_code(String vehicle_code) {
        this.vehicle_code = vehicle_code;
    }

    public Vehicle() {

    }

    public Vehicle(String vehicle_name, byte[] vehicle_image,String imageapi, String vehicle_number, String number_of_passengers,
            String active_state, String vehicle_code) {
        this.vehicle_name = vehicle_name;
        this.vehicle_number = vehicle_number;
        this.number_of_passengers = number_of_passengers;
        this.active_state = active_state;
        this.vehicle_image = vehicle_image;
        this.imageapi=imageapi;
        this.vehicle_code = vehicle_code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageapi() {
        return imageapi;
    }

    public void setImageapi(String imageapi) {
        this.imageapi = imageapi;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public byte[] getVehicle_image() {
        return vehicle_image;
    }

    public void setVehicle_image(byte[] vehicle_image) {
        this.vehicle_image = vehicle_image;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getNumber_of_passengers() {
        return number_of_passengers;
    }

    public void setNumber_of_passengers(String number_of_passengers) {
        this.number_of_passengers = number_of_passengers;
    }

    public String getActive_state() {
        return active_state;
    }

    public void setActive_state(String active_state) {
        this.active_state = active_state;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                "vehicle_name=" + vehicle_name +
                "vehicle_image=" + vehicle_image +
                "vehicle_number=" + vehicle_number +
                "number_of_passengers=" + number_of_passengers +
                "active_state=" + active_state +
                '}';

    }
}

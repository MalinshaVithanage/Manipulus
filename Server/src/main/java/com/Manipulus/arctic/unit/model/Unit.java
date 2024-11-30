package com.Manipulus.arctic.unit.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="unit")
public class Unit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id", nullable = false,updatable = false)
    private int unitId;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "indoor_serial")
    private String indoorSerial;

    @Column(name = "outdoor_serial")
    private String outdoorSerial;

    @Column(name = "commissioned_date")
    private String commissionedDate;

    @Column(name = "owner")
    private String owner;

    @Column(name = "installed_location_name")
    private String installedLocationName;

    @Column(name = "installed_location_address")
    private String installedLocationAddress;

    @Column(name = "installed_parent_location")
    private String installedParentLocation;

    @Column(name = "warranty_period")
    private String warrantyPeriod;

    @Column(name = "unit_price")
    private String unitPrice;

    @Column(name = "status")
    private String status;

    public Unit(){

    }

    /*public Unit(String unit_name,String item_name,String indoor_serial,String outdoor_serial, String commissioned_date,String owner,String installed_location_address,String installed_location_name,String installed_parent_location,String warranty_period,String unit_price,String status)
    {
        this.unitName=unit_name;
        this.itemName=item_name;
        this.indoorSerial=indoor_serial;
        this.outdoorSerial=outdoor_serial;
        this.commissionedDate=commissioned_date;
        this.owner=owner;
        this.installedLocationName=installed_location_name;
        this.installedLocationAddress=installed_location_address;
        this.installedParentLocation=installed_parent_location;
        this.warrantyPeriod=warranty_period;
        this.unitPrice=unit_price;
        this.status=status;
    }*/

    public int getId() {
        return unitId;
    }

    public void setId(int id) {
        this.unitId = id;
    }

    public String getItem_name() {
        return itemName;
    }

    public void setItem_name(String item_name) {
        this.itemName = item_name;
    }

    public String getIndoor_serial() {
        return indoorSerial;
    }

    public void setIndoor_serial(String indoor_serial) {
        this.indoorSerial = indoor_serial;
    }

    public String getOutdoor_serial() {
        return outdoorSerial;
    }

    public void setOutdoor_serial(String outdoor_serial) {
        this.outdoorSerial = outdoor_serial;
    }

    public String getCommissioned_date() {
        return commissionedDate;
    }

    public void setCommissioned_date(String commissioned_date) {
        this.commissionedDate = commissioned_date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInstalled_location_name() {
        return installedLocationName;
    }

    public void setInstalled_location_name(String installed_location_name) {
        this.installedLocationName = installed_location_name;
    }

    public String getInstalled_location_address() {
        return installedLocationAddress;
    }

    public void setInstalled_location_address(String installed_location_address) {
        this.installedLocationAddress = installed_location_address;
    }

    public String getInstalled_parent_location() {
        return installedParentLocation;
    }

    public void setInstalled_parent_location(String installed_parent_location) {
        this.installedParentLocation = installed_parent_location;
    }

    public String getWarranty_period() {
        return warrantyPeriod;
    }

    public void setWarranty_period(String warranty_period) {
        this.warrantyPeriod = warranty_period;
    }

    public String getUnit_price() {
        return unitPrice;
    }

    public void setUnit_price(String unit_price) {
        this.unitPrice = unit_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "Unit{"+
                "id="+unitId+
                "unitName="+unitName +
                "itemName="+itemName+
                "indoorSerial="+indoorSerial+
                "outdoorSerial="+outdoorSerial+
                "commissionedDate="+commissionedDate+
                "owner="+owner+
                "installedLocationName="+installedLocationName+
                "installedLocationAddress="+installedLocationAddress+
                "installedParentLocation="+installedParentLocation+
                "warrantyPeriod="+warrantyPeriod+
                "unitPrice="+unitPrice+
                "status="+status+
                '}';
    }
}

package com.Manipulus.arctic.Agreement.entity;


import jakarta.persistence.*;
@Entity
@Table(name="agreement")
public class Agreement {

    @Id
    @Column(name="agreement_id",length =50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int agreementid;
    @Column(name="unit_id",length =50)
    private int unitid;
    @Column(name="equipments",length =250)
    private String equipment;
    @Column(name="price_per_service",length =20)
    private float price_per_service;
    @Column(name="emergency_service_rate",length =20)
    private float emergency_service_rate;
    @Column(name="type_of_the_service",length =10)
    private String type_of_the_service;
    @Column(name="initiated_date",length =10)
    private String initiated_date;
    @Column(name="expired_date",length =10)
    private String expired_date;

    @Column(name="customerId",length = 10)
    private String NIC;




    public Agreement(int agreementid,int unitid, String equipment, float price_per_service, float emergency_service_rate, String type_of_the_service, String initiated_date, String expired_date,String NIC) {
        this.agreementid = agreementid;
        this.unitid = unitid;
        this.equipment = equipment;
        this.price_per_service = price_per_service;
        this.emergency_service_rate = emergency_service_rate;
        this.type_of_the_service = type_of_the_service;
        this.initiated_date = initiated_date;
        this.expired_date = expired_date;
        this.NIC = NIC;

    }

    public Agreement() {

    }

    public Agreement(int unitid, String equipment, float price_per_service, float emergency_service_rate, String type_of_the_service, String initiated_date, String expired_date,String NIC) {

        this.unitid = unitid;
        this.equipment = equipment;
        this.price_per_service = price_per_service;
        this.emergency_service_rate = emergency_service_rate;
        this.type_of_the_service = type_of_the_service;
        this.initiated_date = initiated_date;
        this.expired_date = expired_date;
        this.NIC = NIC;
    }




    public int getAgreementid() {
        return agreementid;
    }

    public void setAgreementid(int agreementid) {
        this.agreementid = agreementid;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public float getPrice_per_service() {
        return price_per_service;
    }

    public void setPrice_per_service(float price_per_service) {
        this.price_per_service = price_per_service;
    }

    public float getEmergency_service_rate() {
        return emergency_service_rate;
    }

    public void setEmergency_service_rate(float emergency_service_rate) {
        this.emergency_service_rate = emergency_service_rate;
    }

    public String getType_of_the_service() {
        return type_of_the_service;
    }

    public void setType_of_the_service(String type_of_the_service) {
        this.type_of_the_service = type_of_the_service;
    }

    public String getInitiated_date() {
        return initiated_date;
    }

    public void setInitiated_date(String initiated_date) {
        this.initiated_date = initiated_date;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "agreementid=" + agreementid +
                ", unitid=" + unitid +
                ", equipment='" + equipment + '\'' +
                ", price_per_service=" + price_per_service +
                ", emergency_service_rate=" + emergency_service_rate +
                ", type_of_the_service='" + type_of_the_service + '\'' +
                ", initiated_date='" + initiated_date + '\'' +
                ", expired_date='" + expired_date + '\'' +
                ", NIC='" + NIC + '\'' +
                '}';
    }






}

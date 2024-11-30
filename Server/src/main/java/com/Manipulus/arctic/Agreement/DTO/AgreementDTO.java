package com.Manipulus.arctic.Agreement.DTO;


/**
 * A Data Transfer Object (DTO) for storing information about an agreement.
 */

public class AgreementDTO {

    private int agreementid;
    private int unitid;
    private String equipment;
    private float price_per_service;
    private float emergency_service_rate;
    private String type_of_the_service;
    private String initiated_date;
    private String expired_date;

    private String NIC;


    /**
     * Constructs a new AgreementDTO with the given information.
     *
     * @param agreementid the agreement ID
     * @param unitid the ID of the unit associated with the agreement
     * @param equipment the equipment covered by the agreement
     * @param price_per_service the price per service covered by the agreement
     * @param emergency_service_rate the rate for emergency services covered by the agreement
     * @param type_of_the_service the type of service covered by the agreement
     * @param initiated_date the date the agreement was initiated
     * @param expired_date the date the agreement will expire
     */


    public AgreementDTO(int agreementid, int unitid, String equipment, float price_per_service, float emergency_service_rate, String type_of_the_service, String initiated_date, String expired_date,String NIC) {
        this.agreementid = agreementid;
        this.unitid = unitid;
        this.equipment = equipment;
        this.price_per_service = price_per_service;
        this.emergency_service_rate = emergency_service_rate;
        this.type_of_the_service = type_of_the_service;
        this.initiated_date = initiated_date;
        this.expired_date = expired_date;
        this.NIC=NIC;

    }


    /**
     * Constructs a new empty AgreementDTO.
     */

    public AgreementDTO() {
    }

    /**
     * Returns the agreement ID.
     *
     * @return the agreement ID
     */


    public int getAgreementid() {
        return agreementid;
    }


    /**
     * Sets the agreement ID.
     *
     * @param agreementid the agreement ID to set
     */
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
        return "AgreementDTO{" +
                "agreementid=" + agreementid +
                ", unitid=" + unitid +
                ", equipment='" + equipment + '\'' +
                ", price_per_service=" + price_per_service +
                ", emergency_service_rate=" + emergency_service_rate +
                ", type_of_the_service='" + type_of_the_service + '\'' +
                ", initiated_date='" + initiated_date + '\'' +
                ", expired_date='" + expired_date + '\'' +
                ", expired_date='" + expired_date + '\'' +
                '}';
    }
}

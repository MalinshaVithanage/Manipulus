package com.Manipulus.arctic.Agreement.DTO;

public class PdfDTO{

  public int agreementid;



    /**
     * Constructs a new AgreementDTO with the given information.
     *
     * @param agreementid the agreement ID

     */


    public PdfDTO(int agreementid) {
        this.agreementid = agreementid;

    }


    /**
     * Constructs a new empty AgreementDTO.
     */

    public PdfDTO() {
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



    @Override
    public String toString() {
        return "PdfDTO{" +
                "agreementid=" + agreementid +
                '}';
    }
}

package com.Manipulus.arctic.Agreement.Service;

import com.Manipulus.arctic.Agreement.DTO.AgreementDTO;
import com.Manipulus.arctic.Agreement.DTO.AgreementSaveDTO;
import com.Manipulus.arctic.Agreement.DTO.AgreementUpdateDTO;


import java.util.List;
/**
 * Service interface for managing agreements.
 */

public interface AgreementService {
    /**
     * Adds a new agreement to the system.
     *
     * @param agreementSaveDTO the agreement data to add
     * @return the ID of the newly created agreement
     */

    int addAgreement(AgreementSaveDTO agreementSaveDTO);

    /**
     * Retrieves a list of all agreements in the system.
     *
     * @return a list of all agreements
     */


    List<AgreementDTO> getAllAgreement();

    /**
     * Updates an existing agreement in the system.
     *
     * @param agreementUpdateDTO the updated agreement data
     * @return a message indicating the result of the update
     */

    String updateAgreements(AgreementUpdateDTO agreementUpdateDTO);
    /**
     * Deletes an agreement from the system.
     *
     * @param id the ID of the agreement to delete
     * @return true if the agreement was deleted, false otherwise
     */

    boolean deleteAgreement(int id);
}

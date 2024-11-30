package com.Manipulus.arctic.Agreement.controller;


import com.Manipulus.arctic.Agreement.DTO.AgreementDTO;
import com.Manipulus.arctic.Agreement.DTO.AgreementSaveDTO;
import com.Manipulus.arctic.Agreement.DTO.AgreementUpdateDTO;
import com.Manipulus.arctic.Agreement.DTO.PdfDTO;
import com.Manipulus.arctic.Agreement.Service.AgreementService;
import com.Manipulus.arctic.Agreement.Service.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
/**
 * REST controller for managing agreements.
 */
@RestController
@CrossOrigin
@RequestMapping("api/v1/agreement")

public class AgreementController {

    @Autowired
    private AgreementService agreementService;
    /**
     * Adds a new agreement to the system.
     *
     * @param agreementSaveDTO the agreement data to add
     * @return the ID of the newly created agreement
     */

    @Autowired
    private PDFGeneratorService pdfGeneratorService;



    @PostMapping(path="/save")
    public int saveAgreement(@RequestBody AgreementSaveDTO agreementSaveDTO)
    {
        int id = agreementService.addAgreement(agreementSaveDTO);
        return id;
    }


    /**
     * Retrieves a list of all agreements in the system.
     *
     * @return a list of all agreements
     */

    @GetMapping(path="/getAllAgreement")
    public List<AgreementDTO> getAllAgreement()
    {
        List<AgreementDTO>allAgreements = agreementService.getAllAgreement();
        return allAgreements;
    }


    /**
     * Updates an existing agreement in the system.
     *
     * @param agreementUpdateDTO the updated agreement data
     * @return a message indicating the result of the update
     */

    @PutMapping(path="/update")
    public String updateAgreement(@RequestBody AgreementUpdateDTO agreementUpdateDTO)
    {
        String id = agreementService.updateAgreements(agreementUpdateDTO);
        return id;
    }

    @PutMapping(path="/passId")
    public PdfDTO passId(@RequestBody PdfDTO pdfDTO)
    {
      return pdfGeneratorService.passId(pdfDTO);
    }


    /**
     * Deletes an agreement from the system.
     *
     * @param id the ID of the agreement to delete
     * @return a message indicating the result of the delete operation
     */

    @DeleteMapping(path="/deleteagreement/{id}")
    public String deleteAgreement(@PathVariable(value = "id")int id)
    {
        boolean deleteagreement = agreementService.deleteAgreement(id);
        return "deleted";
    }


}

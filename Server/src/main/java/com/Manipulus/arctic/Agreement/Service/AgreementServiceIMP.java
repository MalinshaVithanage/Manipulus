package com.Manipulus.arctic.Agreement.Service;

import com.Manipulus.arctic.Agreement.DTO.AgreementDTO;
import com.Manipulus.arctic.Agreement.DTO.AgreementSaveDTO;
import com.Manipulus.arctic.Agreement.DTO.AgreementUpdateDTO;
import com.Manipulus.arctic.Agreement.Repo.AgreementRepo;
import com.Manipulus.arctic.Agreement.entity.Agreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgreementServiceIMP implements AgreementService{


    @Autowired
    private AgreementRepo agreementRepo;
    @Override
    public int addAgreement(AgreementSaveDTO agreementSaveDTO) {
        Agreement agreement = new Agreement(
                agreementSaveDTO.getUnitid(),
                agreementSaveDTO.getEquipment(),
                agreementSaveDTO.getPrice_per_service(),
                agreementSaveDTO.getEmergency_service_rate(),
                agreementSaveDTO.getType_of_the_service(),
                agreementSaveDTO.getInitiated_date(),
                agreementSaveDTO.getExpired_date(),
                agreementSaveDTO.getNIC()

        );
        agreementRepo.save(agreement);
        return agreement.getAgreementid();

    }

    @Override
    public List<AgreementDTO> getAllAgreement() {
        List<Agreement> getAgreements = agreementRepo.findAll();
        List<AgreementDTO> agreementDTOList = new ArrayList<>();
        for (Agreement a:getAgreements) {
            AgreementDTO agreementDTO = new AgreementDTO(
                    a.getAgreementid(),
                    a.getUnitid(),
                    a.getEquipment(),
                    a.getPrice_per_service(),
                    a.getEmergency_service_rate(),
                    a.getType_of_the_service(),
                    a.getInitiated_date(),
                    a.getExpired_date(),
                    a.getNIC()
            );
            agreementDTOList.add(agreementDTO);
        }
        return agreementDTOList;

    }



    @Override
    public String updateAgreements(AgreementUpdateDTO agreementUpdateDTO) {

        if(agreementRepo.existsById(agreementUpdateDTO.getAgreementid()))
        {
            Agreement agreement = agreementRepo.getById(agreementUpdateDTO.getAgreementid());

            agreement.setUnitid(agreementUpdateDTO.getUnitid());
            agreement.setEquipment(agreementUpdateDTO.getEquipment());
            agreement.setPrice_per_service(agreementUpdateDTO.getPrice_per_service());
            agreement.setEmergency_service_rate(agreementUpdateDTO.getEmergency_service_rate());
            agreement.setType_of_the_service(agreementUpdateDTO.getType_of_the_service());
            agreement.setInitiated_date(agreementUpdateDTO.getInitiated_date());
            agreement.setExpired_date(agreementUpdateDTO.getExpired_date());
            agreement.setNIC(agreementUpdateDTO.getNIC());

            agreementRepo.save(agreement);
        }
        else
        {
            System.out.println("Agreement id do not exist");
        }

        return null;
    }

    @Override
    public boolean deleteAgreement(int id) {

        if(agreementRepo.existsById(id))
        {
            agreementRepo.deleteById(id);
        }
        else
        {
            System.out.println("Agreement id not found");
        }
        return true;
    }


}
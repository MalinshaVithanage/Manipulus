package com.Manipulus.arctic.siteVisit.service;
import com.Manipulus.arctic.siteVisit.exception.UserNotFoundException;
import com.Manipulus.arctic.siteVisit.repo.SiteVisitRepo;
import com.Manipulus.arctic.siteVisit.siteVisit.SiteVisit;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SiteVisitService {

        private final SiteVisitRepo siteVisitRepo;


        @Autowired
        public SiteVisitService(SiteVisitRepo siteVisitRepo) {

            this.siteVisitRepo = siteVisitRepo;
        }

        public SiteVisit addSiteVisit(SiteVisit siteVisit){
            siteVisit.setSiteVisitCode(UUID.randomUUID().toString());

            return siteVisitRepo.save(siteVisit);
        }

        public List<SiteVisit> findAllSiteVisits(){

            return siteVisitRepo.findAll();
        }
        public SiteVisit updateSiteVisit(SiteVisit siteVisit){

            return siteVisitRepo.save(siteVisit);
        }
        public SiteVisit findSiteVisitBySiteVisitId(Long siteVisitId){
            return siteVisitRepo.findSiteVisitBySiteVisitId(siteVisitId)
                    .orElseThrow(()->new UserNotFoundException("User by id "+siteVisitId +"was not found."));
        }
        public void deleteSiteVisit(Long siteVisitId){

            siteVisitRepo.deleteSiteVisitBySiteVisitId(siteVisitId);
        }



    }


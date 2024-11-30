package com.Manipulus.arctic.siteVisit.processor;

import com.Manipulus.arctic.customer.model.Customer;
import com.Manipulus.arctic.job.repository.JobRepository;
import com.Manipulus.arctic.siteVisit.SiteVisitResource;
import com.Manipulus.arctic.siteVisit.repo.SiteVisitRepo;
import com.Manipulus.arctic.siteVisit.service.SiteVisitService;
import com.Manipulus.arctic.siteVisit.siteVisit.SiteVisit;
import jakarta.inject.Inject;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.Manipulus.arctic.job.model.Job;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class JobCardProcessor {

    @Autowired
    public SiteVisitRepo siteVisitRepo;
    @Autowired
    private JobCardProcessor jobCardProcessor;
    @Autowired
    private SiteVisitService siteVisitService;
    @Autowired
    private SiteVisitResource siteVisitResource;
    @Autowired
    public JobRepository jobRepository;

    public ByteArrayOutputStream generateReport() throws JRException {
        List<SiteVisit> siteVisits = siteVisitRepo.findAll();

        // Load the JasperReport template
        String filePath = "C:\\Users\\HP\\Desktop\\Manipulus 9.0\\Manipulus\\Server\\src\\main\\resources\\template\\jobCard.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);

        // Prepare parameters for the report
        Map<String, Object> parameters = new HashMap<>();

        // Pass the data to the report
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(siteVisits);

        // Generate the report
        // JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to a ByteArrayOutputStream
        ByteArrayOutputStream reportStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, reportStream);

        return reportStream;
    }

    public ByteArrayOutputStream generateReportBySiteVisitId(Long siteVisitId) throws JRException {
        SiteVisit siteVisit = siteVisitService.findSiteVisitBySiteVisitId(siteVisitId); // Replace siteVisitId with the actual ID of the site visit you want to display
        List<SiteVisit> siteVisits = new ArrayList<>();
        siteVisits.add(siteVisit);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(siteVisits);


        // Check if the site visit exists
        if (siteVisit == null) {
            throw new IllegalArgumentException("Invalid site visit ID");
        }
        // Load the JasperReport template
        String filePath = "C:\\Users\\HP\\Desktop\\Manipulus 9.0\\Manipulus\\Server\\src\\main\\resources\\template\\jobCard.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);


//
        // Create a map of parameters to be passed to the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sitVisitId", siteVisitId);
        parameters.put("assignedJob",siteVisit.getAssignedJob() );
//        parameters.put("job",siteVisit.getJobs() );

        // Find the job and customer by assigned job ID
//      Job assignedJob = siteVisit.getAssignedJob();
        Job job = jobRepository.findById(siteVisit.getAssignedJob()).orElseThrow(() -> new IllegalArgumentException("Invalid assigned job ID"));
        Customer customer = job.getCustomer();

        parameters.put("customerName", customer.getName());
        parameters.put("customerAddress", customer.getAddress());
        parameters.put("customerEmail", customer.getEmail());
        parameters.put("customerContactNumber", customer.getContactNumber());
        parameters.put("customerDesignation", customer.getDesignation());
        parameters.put("contactPersonName", customer.getContactPersonName());


// Create an empty data source (since the report doesn't require any specific data source)
        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(siteVisits);

        // Fill the report with data and parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the filled report to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream;
    }
}

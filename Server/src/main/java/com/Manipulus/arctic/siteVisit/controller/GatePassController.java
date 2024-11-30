package com.Manipulus.arctic.siteVisit.controller;

import com.Manipulus.arctic.siteVisit.processor.GatePassProcessor;
import com.Manipulus.arctic.siteVisit.repo.SiteVisitRepo;
import com.Manipulus.arctic.siteVisit.service.SiteVisitService;
import com.Manipulus.arctic.vehicle.repository.VehicleRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

//import static java.lang.System.LoggerFinder.service;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/gate-pass")
public class GatePassController {
    @Autowired
    private GatePassProcessor gatePassProcessor;


    private SiteVisitService siteVisitService ;
    private VehicleRepository vehicleRepository;

    private SiteVisitRepo siteVisitRepo;


    public GatePassController(GatePassProcessor gatePassProcessor, SiteVisitRepo siteVisitRepo) {
        this.gatePassProcessor = gatePassProcessor;
        this.siteVisitRepo = siteVisitRepo;

    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> getGatePass() throws JRException {
        ByteArrayOutputStream reportStream = gatePassProcessor.generateReport();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "GatePass.pdf");

        return ResponseEntity.ok()
                .headers(headers)

                .body(reportStream.toByteArray());
    }
    @GetMapping("/printGatePass/{siteVisitId}")
    public ResponseEntity<byte[]> printGatePass(@PathVariable Long siteVisitId) throws JRException {
        ByteArrayOutputStream reportStream = gatePassProcessor.generateReportBySiteVisitId(siteVisitId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "GatePass.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(reportStream.toByteArray());
    }

//    @GetMapping("/report")
//    public ResponseEntity getGatePass() throws JRException {
//        ByteArrayOutputStream reportStream = gatePassProcessor.generateReport();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
//
//        return new ResponseEntity<>(reportStream.toByteArray(), httpHeaders, HttpStatus.OK);

//@GetMapping("/gatePassPdf/export")
//public void createPDF(HttpServletResponse response)throws IOException,JRException{
//    response.setContentType("application/pdf");
//    DateFormat dateFormatter =new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//    String currentDateTime =dateFormatter.format(new Date());
//
//    String headerKey = "Content-Disposition";
//    String headerValue = "attachment; fileName=pdf_"+currentDateTime+".pdf";
//    response.setHeader(headerKey,headerValue);
//    gatePassProcessor.exportJasperReport(response);
//}


}

package com.Manipulus.arctic.Agreement.controller;

import com.Manipulus.arctic.Agreement.Service.PDFGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller class for generating and exporting a PDF file.
 */
@Controller
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    /**
     * Constructor for creating a new PDFExportController instance.
     * @param pdfGeneratorService The PDFGeneratorService to be used for generating the PDF file.
     */
    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    /**
     * Handler method for generating and exporting a PDF file.
     * @param response The HttpServletResponse object for sending the generated PDF file.
     * @throws IOException Thrown when an IO exception occurs while exporting the PDF file.
     */
    @GetMapping("/pdf")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey ="Content-Disposition";
        String headerValue ="attachment; filename=pdf_" + currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }
}

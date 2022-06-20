package com.proj.demo.controller;

import com.proj.demo.service.ISender;
import com.proj.demo.service.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReportController {

    ReportService reportService;
    ISender senderService;

    @GetMapping("/caterer/{productOrderId}/{responsible}/{email}")
    public ResponseEntity<byte[]> generateCatererReport(@PathVariable Integer productOrderId, @PathVariable String responsible, @PathVariable String email) throws Exception {
        byte[] bytes = reportService.generateCatererReport(productOrderId, responsible, email);
        sendInvoiceToDeliver(productOrderId, reportService.generateDeliverInvoice(productOrderId), email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=report-order" + productOrderId + ".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(bytes);
    }

    @GetMapping("/deliver/{productOrderId}")
    public ResponseEntity<byte[]> generateDeliverInvoice(@PathVariable Integer productOrderId) throws Exception {
        byte[] bytes = reportService.generateDeliverInvoice(productOrderId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=invoice" + productOrderId + ".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(bytes);
    }

    private void sendInvoiceToDeliver(Integer productOrderId, byte[] data, String email){
        new Thread(() ->
                senderService.send(email, "Накладная на заявку " + productOrderId, data, "invoice" + productOrderId + ".pdf"))
                .start();
    }


}

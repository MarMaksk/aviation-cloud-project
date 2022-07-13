package org.aviation.projects.flightcatering.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.flightcatering.service.ISender;
import org.aviation.projects.flightcatering.service.ReportService;
import org.jfree.util.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(value = "Report and invoice controller")
public class ReportController {

    ReportService reportService;
    ISender senderService;

    @GetMapping("/caterer/{productOrderId}/{responsible}/{email}")
    @ApiOperation(value = "Caterer report", response = Iterable.class)
    public ResponseEntity<byte[]> generateCatererReport(@PathVariable Integer productOrderId, @PathVariable String responsible, @PathVariable String email) throws Exception {
        byte[] bytes = reportService.generateCatererReport(productOrderId, responsible, email);
        sendInvoiceToDeliver(productOrderId, reportService.generateDeliverInvoice(productOrderId), email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=report-order" + productOrderId + ".pdf");
        Log.info("Generate caterer report for order: " + productOrderId);
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(bytes);
    }

    @GetMapping("/deliver/{productOrderId}")
    @ApiOperation(value = "Delivery invoice", response = Iterable.class)
    public ResponseEntity<byte[]> generateDeliverInvoice(@PathVariable Integer productOrderId) throws Exception {
        byte[] bytes = reportService.generateDeliverInvoice(productOrderId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=invoice" + productOrderId + ".pdf");
        Log.info("Generate deliver invoice for order: " + productOrderId);
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(bytes);
    }

    private void sendInvoiceToDeliver(Integer productOrderId, byte[] data, String email) {
        Log.info("Send invoice to deliver for order: " + productOrderId);
        new Thread(() ->
                senderService.send(email, "Накладная на заявку " + productOrderId, data, "invoice" + productOrderId + ".pdf"))
                .start();
    }


}

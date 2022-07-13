package org.aviation.projects.flightcatering.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.aviation.projects.flightcatering.entity.Order;
import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.repository.OrderRepository;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReportService {

    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    OrderRepository orderRepository;

    @Transactional
    public byte[] generateCatererReport(Integer productOrderId, String responsible, String email) throws Exception {
        Log.info("generateCatererReport method called in ReportService");
        Order order = orderRepository.findByProductOrderIdAndDeletedFalse(productOrderId).orElseThrow(NoSuchOrderException::new);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getProducts());
        Map<String, Object> map = new HashMap<>();
        map.put("responsible", responsible);
        map.put("email", email);
        map.put("orderNumber", order.getProductOrderId().toString().replace("-", ""));
        map.put("status", order.getStatus().getStatus());
        map.put("lastDate", order.getLastDate().format(FORMATTER));
        map.put("icao", order.getIcaoCode());
        map.put("iata", order.getIataCode());
        JasperPrint jasperPrint = JasperFillManager
                .fillReport(getClass().getClassLoader().getResourceAsStream("reports/catering.jasper"), map, dataSource);
        Log.info("generateCatererReport method finished in ReportService");
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Transactional
    public byte[] generateDeliverInvoice(Integer productOrderId) throws Exception {
        Log.info("generateDeliverInvoice method called in ReportService");
        Order order = orderRepository.findByProductOrderIdAndDeletedFalse(productOrderId).orElseThrow(NoSuchOrderException::new);
        order.getProducts().removeAll(order.getDeliveredProducts());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getProducts());
        Map<String, Object> map = new HashMap<>();
        map.put("orderNumber", order.getProductOrderId().toString().replace("-", ""));
        map.put("status", order.getStatus().getStatus());
        map.put("lastDate", order.getLastDate().format(FORMATTER));
        map.put("icao", order.getIcaoCode());
        map.put("iata", order.getIataCode());
        JasperPrint jasperPrint = JasperFillManager
                .fillReport(getClass().getClassLoader().getResourceAsStream("reports/delivery.jasper"), map, dataSource);
        Log.info("generateDeliverInvoice method finished in ReportService");
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}

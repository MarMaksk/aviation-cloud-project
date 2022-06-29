package org.aviation.projects.flightcatering.service;

import org.aviation.projects.flightcatering.entity.Order;
import org.aviation.projects.flightcatering.entity.Product;
import org.aviation.projects.flightcatering.entity.Tag;
import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReportService {

    static String PATH = "flight-catering/src/main/resources/reports/";
    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    OrderRepository orderRepository;

    public byte[] generateCatererReport(Integer productOrderId, String responsible, String email) throws Exception {
        Order order = orderRepository.findByProductOrderIdAndDeletedFalse(productOrderId).orElseThrow(NoSuchOrderException::new);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getProducts());
        Map<String, Object> map = new HashMap<>();
        map.put("responsible", responsible);
        map.put("email", email);
        map.put("orderNumber", order.getProductOrderId().toString().replace("-", ""));
        map.put("status", order.getStatus().getName());
        map.put("lastDate", order.getLastDate().format(FORMATTER));
        map.put("icao", order.getIcaoCode());
        map.put("iata", order.getIataCode());
        JasperPrint jasperPrint = JasperFillManager
                .fillReport(getClass().getClassLoader().getResourceAsStream("reports/caterer-report.jasper"), map, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generateDeliverInvoice(Integer productOrderId) throws Exception {
        Order order = orderRepository.findByProductOrderIdAndDeletedFalse(productOrderId).orElseThrow(NoSuchOrderException::new);
        order.getProducts().removeAll(order.getDeliveredProducts());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order.getProducts());
        Map<String, Object> map = new HashMap<>();
        map.put("orderNumber", order.getProductOrderId().toString().replace("-", ""));
        map.put("status", order.getStatus().getName());
        map.put("lastDate", order.getLastDate().format(FORMATTER));
        map.put("icao", order.getIcaoCode());
        map.put("iata", order.getIataCode());
        JasperPrint jasperPrint = JasperFillManager
                .fillReport(getClass().getClassLoader().getResourceAsStream("reports/delivery-invoice.jasper"), map, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}

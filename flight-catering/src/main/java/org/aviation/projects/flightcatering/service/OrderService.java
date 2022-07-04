package org.aviation.projects.flightcatering.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.aviation.projects.flightcatering.dto.OrderDTO;
import org.aviation.projects.flightcatering.entity.Order;
import org.aviation.projects.flightcatering.exception.NoSuchOrderException;
import org.aviation.projects.flightcatering.repository.OrderRepository;
import org.aviation.projects.flightcatering.service.mapper.OrderDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.InfoForOrder;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.aviation.projects.commons.service.CRUD;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService implements CRUD<OrderDTO> {

    final OrderDTOMapper orderMapper;
    final OrderRepository repository;
    final ModelMapper mapper;
    final MailSenderService mailSenderService;

    AtomicInteger productCount;

    public OrderService(OrderDTOMapper orderMapper,
                        OrderRepository repository,
                        ModelMapper mapper,
                        MailSenderService mailSenderService,
                        MeterRegistry meterRegistry) {
        this.orderMapper = orderMapper;
        this.repository = repository;
        this.mapper = mapper;
        this.mailSenderService = mailSenderService;
        productCount = new AtomicInteger();
        meterRegistry.gauge("productCount", productCount);
    }

    public Page<OrderDTO> findAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable).map(orderMapper::toDTO);
    }

    public OrderDTO findByproductOrderId(Integer productOrderId) throws NoSuchOrderException {
        Order order = repository.findByProductOrderIdAndDeletedFalse(
                productOrderId
        ).orElseThrow(NoSuchOrderException::new);
        return orderMapper.toDTO(order);
    }

    public void createByInfo(InfoForOrder info) {
        Order order = new Order();
        order.setIataCode(info.getIataCode());
        order.setIcaoCode(info.getIcaoCode());
        order.setLastDate(info.getDeparture().minusHours(4));
        order.setProductOrderId(info.getProductOrderId());
        order.setStatus(DeliveryStatus.CREATED);
        repository.save(order);
        mailSenderService.sendForCaterers(
                "New order", "New order has been created" +
                        "\nProduct order id: " + order.getProductOrderId() +
                        "\nIATA code: " + order.getIataCode() +
                        "\nICAO code: " + order.getIcaoCode() +
                        "\nLast date: " + order.getLastDate());
    }

    @Override
    public void create(OrderDTO dto) throws Exception {
        repository.save(orderMapper.toEntity(dto));
        mailSenderService.sendForCaterers(
                "New order", "New order has been created" +
                        "\nProduct order id: " + dto.getProductOrderId() +
                        "\nIATA code: " + dto.getIataCode() +
                        "\nICAO code: " + dto.getIcaoCode() +
                        "\nLast date: " + dto.getLastDate());
    }

    @Override
    public OrderDTO findByCode(String productOrderId) throws Exception {
        return orderMapper.toDTO(repository.findByProductOrderIdAndDeletedFalse(Integer.parseInt(productOrderId)).orElseThrow(NoSuchOrderException::new));
    }


    @Override
    public OrderDTO update(OrderDTO dto) throws Exception {
        Order order = repository.findByProductOrderIdAndDeletedFalse(dto.getProductOrderId())
                .orElseThrow(NoSuchOrderException::new);
        Order newOrder = orderMapper.toEntity(dto);
        mapper.map(newOrder, order);
        order.setProducts(newOrder.getProducts());
        order.setDeliveredProducts(newOrder.getDeliveredProducts());
        Order save = repository.save(order);
        if (save.getStatus() == DeliveryStatus.FINISHED)
            productCount.set(save.getDeliveredProducts().size());
        return orderMapper.toDTO(save);
    }

    @Override
    public void delete(String productOrderId) throws NoSuchOrderException {
        Order order = repository.findByProductOrderIdAndDeletedFalse(Integer.parseInt(productOrderId)).orElseThrow(NoSuchOrderException::new);
        order.setDeleted(true);
        repository.save(order);
    }
}

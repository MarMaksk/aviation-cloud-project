package org.aviation.projects.flightcatering.service.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.commons.entity.enums.DeliveryStatus;
import org.aviation.projects.commons.service.mapper.EntityToDTOMapper;
import org.aviation.projects.flightcatering.dto.OrderDTO;
import org.aviation.projects.flightcatering.dto.ProductDTO;
import org.aviation.projects.flightcatering.entity.Order;
import org.aviation.projects.flightcatering.entity.Product;
import org.aviation.projects.flightcatering.exception.NoSuchProductException;
import org.aviation.projects.flightcatering.repository.ProductRepository;
import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderDTOMapper implements EntityToDTOMapper<Order, OrderDTO> {

    static Logger LOG = LoggerFactory.getLogger(OrderDTOMapper.class);
    ModelMapper mapper;
    ProductRepository productRepository;
    ProductDTOMapper productDTOMapper;

    @Override
    public Order toEntity(OrderDTO dto) {
        Log.info("toEntity method called in OrderDTOMapper");
        Order map = mapper.map(dto, Order.class);
        Set<Product> products = new HashSet<>();
        Set<Product> deliveredProducts = new HashSet<>();
        for (ProductDTO prod : dto.getProducts()) {
            try {
                products.add(productRepository.findByCodeAndDeletedFalse(prod.getCode()).orElseThrow(NoSuchProductException::new));
            } catch (NoSuchProductException ex) {
                LOG.warn(ex.getMessage());
            }
        }
        for (ProductDTO prod : dto.getDeliveredProducts()) {
            try {
                deliveredProducts.add(productRepository.findByCodeAndDeletedFalse(prod.getCode()).orElseThrow(NoSuchProductException::new));
            } catch (NoSuchProductException ex) {
                LOG.warn(ex.getMessage());
            }
        }
        map.setProducts(products);
        map.setDeliveredProducts(deliveredProducts);
        if (dto.getStatus() != null)
            map.setStatus(DeliveryStatus.fromString(dto.getStatus()));
        return map;
    }

    @Override
    public OrderDTO toDTO(Order entity) {
        Log.info("toDTO method called in OrderDTOMapper");
        OrderDTO map = mapper.map(entity, OrderDTO.class);
        List<ProductDTO> productDTOS = productDTOMapper.toDTOs(new ArrayList<>(entity.getProducts()));
        map.setProducts(new HashSet<>(productDTOS));
        map.setStatus(entity.getStatus().getName());
        return map;
    }
}

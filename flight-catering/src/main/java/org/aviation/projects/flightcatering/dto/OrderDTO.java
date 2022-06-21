package org.aviation.projects.flightcatering.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDTO {

    /* airplane */
    @Size(max = 4)
    private String icaoCode;
    /* airport */
    @Size(max = 3)
    private String iataCode;
    /*
    Крайнее время для поставки
    Надо доставить за 4 часа до конца
     */
    private LocalDateTime lastDate;
    /*
    Время когда доставка была осуществлена
     */
    private LocalDateTime deliveryTime;
    /*
    Статус доставки
     */
    private String status;
    /*
        Идентификатор с другого сервиса
         */
    private Integer productOrderId;

    private Set<ProductDTO> products;

    private Set<ProductDTO> deliveredProducts;
}

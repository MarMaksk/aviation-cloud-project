package org.aviation.projects.flightcatering.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDTO {

    /* airplane */
    @Size(max = 3)
    @ApiModelProperty(value = "Airplane code", example = "M01")
    private String icaoCode;
    /* airport */
    @Size(max = 4)
    @ApiModelProperty(value = "Airport code", example = "1111")
    private String iataCode;
    /*
    Крайнее время для поставки
    Надо доставить за 4 часа до конца
     */
    @ApiModelProperty(value = "Last date", example = "2020-01-01T00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastDate;
    /*
    Время когда доставка была осуществлена
     */
    @ApiModelProperty(value = "Delivery time", example = "2020-01-01T00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;
    /*
    Статус доставки
     */
    @ApiModelProperty(value = "Delivery status", example = "DELIVERED")
    private String status;
    /*
        Идентификатор с другого сервиса
         */
    @ApiModelProperty(value = "Product order id", example = "1")
    private Integer productOrderId;

    @ApiModelProperty(value = "Products", example = "[{\"id\":1,\"name\":\"Product 1\",\"price\":100},{\"id\":2,\"name\":\"Product 2\",\"price\":200}]")
    private Set<ProductDTO> products;

    @ApiModelProperty(value = "Delivered products", example = "[{\"id\":1,\"name\":\"Product 1\",\"price\":100},{\"id\":2,\"name\":\"Product 2\",\"price\":200}]")
    private Set<ProductDTO> deliveredProducts;
}

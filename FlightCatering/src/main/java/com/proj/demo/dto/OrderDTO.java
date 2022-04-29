package com.proj.demo.dto;

import com.proj.demo.enums.DeliveryStatusEnum;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    private DeliveryStatusEnum status;

}

package org.aviation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoForOrder {

    private String icaoCode;
    private String iataCode;
    private LocalDateTime departure;
    private Integer productOrderId;

}

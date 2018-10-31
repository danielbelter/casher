package com.app.casher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {
    private String no;
    private String effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
}

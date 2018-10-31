package com.app.casher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cash {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}

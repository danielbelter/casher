package com.app.casher.service;

import com.app.casher.model.Cash;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class ParserService {
    public BigDecimal avgValueOfBuying(Cash cash) {
        BigDecimal rates = cash.getRates().stream()
                .map(rate -> rate.getBid())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return rates.divide(new BigDecimal(cash.getRates().size()), RoundingMode.CEILING);
    }

    public BigDecimal avgValueOfSelling(Cash cash) {
        BigDecimal rates = cash.getRates().stream()
                .map(rate -> rate.getAsk())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return rates.divide(new BigDecimal(cash.getRates().size()), RoundingMode.CEILING);
    }

    public BigDecimal standardDeviation(Cash cash) {
        double average = avgValueOfSelling(cash).doubleValue();
        double variance = 0;
        for (int i = 0; i < cash.getRates().size(); i++) {
            variance += (cash.getRates().get(i).getAsk().doubleValue() - average) * ((cash.getRates().get(i).getAsk().doubleValue() - average));

        }
        variance = variance / cash.getRates().size();
        variance = Math.sqrt(variance);
        BigDecimal bigDecimalVariance = BigDecimal.valueOf(variance);
        bigDecimalVariance = bigDecimalVariance.setScale(4, RoundingMode.DOWN);
        return bigDecimalVariance;
    }

}


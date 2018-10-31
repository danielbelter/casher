package com.app.casher.parsers;

import com.app.casher.exceptions.ExceptionMessage;
import com.app.casher.model.Cash;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static String ALL_CURRENCIES_FROM_A_TABLE;

    static public Cash getAllCur(String code, String dateOne, String dateTwo) {
        Gson gson = new Gson();
        Cash cash;
        BufferedReader reader = null;

        urlChecker(code, dateOne, dateTwo);
        try {
            URL url = new URL(ALL_CURRENCIES_FROM_A_TABLE);
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json = JsonReader.readAll(reader);
            cash = gson.fromJson(json, Cash.class);

        } catch (Exception e) {
            throw new ExceptionMessage("GET ALL CURRENCIES METHOD ERROR", LocalDateTime.now());
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                throw new ExceptionMessage("READER CLOSE EXCEPTION", LocalDateTime.now());
            }
        }
        return cash;
    }

    private static void urlChecker(String code, String dateOne, String dateTwo) {
        String codeRegex = "[a-zA-Z]{3}";
        if (!code.isEmpty() && !dateOne.isEmpty() && !dateTwo.isEmpty()) {
            try {
                LocalDate date1 = LocalDate.parse(dateOne, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate date2 = LocalDate.parse(dateTwo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (code.matches(codeRegex) && date1.isBefore(date2)) {
                    ALL_CURRENCIES_FROM_A_TABLE = "http://api.nbp.pl/api/exchangerates/rates/C/" + code + "/" + dateOne + "/" + dateTwo + "/?format=json";
                } else {
                    throw new ExceptionMessage("GET ALL CURRENCIES URL CHECKER ERROR 2", LocalDateTime.now());
                }
            } catch (Exception e) {
                throw new ExceptionMessage("WRONG DATA ", LocalDateTime.now());
            }
        } else {
            throw new ExceptionMessage("DATA IS EMPTY", LocalDateTime.now());
        }
    }
}

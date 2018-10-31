package com.app.casher.controllers;

import com.app.casher.model.Cash;
import com.app.casher.parsers.Parser;
import com.app.casher.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    ParserService parserService;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/parse")
    public String main(@RequestParam("code") String code, @RequestParam("dateOne") String dateOne, @RequestParam("dateTwo") String dateTwo, Model model) throws IOException {
        Cash cash = Parser.getAllCur(code, dateOne, dateTwo);
        model.addAttribute("buyingValue", parserService.avgValueOfBuying(cash));
        model.addAttribute("standardDeviation", parserService.standardDeviation(cash));
        return "result";
    }
}


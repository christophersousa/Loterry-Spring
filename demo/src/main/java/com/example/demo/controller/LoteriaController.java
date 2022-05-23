package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loteria")
public class LoteriaController {

    @RequestMapping("/list")
    public String getListLoteria() {
        return "loteria/listar_loteria";
    }

}

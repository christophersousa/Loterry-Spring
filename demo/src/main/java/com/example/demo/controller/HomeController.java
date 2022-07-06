package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.cliente.ClienteServiceImp;

@Controller
public class HomeController {

    @Autowired
    ClienteServiceImp clienteService;

    @RequestMapping("/home")
    public String getHome(Model m) {
        m.addAttribute("menu", "home");
        m.addAttribute("user", clienteService.buscarCliente());
        return "index";
    }
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ClienteRepository clienteRepository;

    @RequestMapping("/cadastrar")
    public String cadastrar_user(Model m) {
        m.addAttribute("menu", "cadastrar_user");
        m.addAttribute("cliente", new Cliente());
        return "admin/registerUser";
    }

    @RequestMapping("/lista")
    public String getUsers(Model m) {
        m.addAttribute("menu", "listar_user");
        m.addAttribute("clientes", clienteRepository.findAll());
        return "admin/listerUser";
    }
}

package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Cliente;
import com.example.demo.service.imp.ClienteServiceImp;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    ClienteServiceImp clienteService;

    private Cliente cliente;

    @RequestMapping("/saldo")
    public String getListLoteria(Model m) {
        this.cliente = clienteService.buscarCliente();
        m.addAttribute("menu", "saldo");
        m.addAttribute("user", this.cliente);
        return "user/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Cliente cliente, BindingResult validation, ModelAndView mav,
            RedirectAttributes attr) {
        if (!validation.hasErrors()) {
            System.out.println(cliente);
            clienteService.updateUser(cliente);
            mav.setViewName("redirect:/auth");
            attr.addFlashAttribute("mensagem", cliente.getNome() + " cadastrada(o) com sucesso!");
            attr.addFlashAttribute("pessoa", cliente);
        } else {
            System.out.println("Cliente não foi salvo");
            mav.addObject("mensagem", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("redirect:/home");
        }
        return mav;
    }
}

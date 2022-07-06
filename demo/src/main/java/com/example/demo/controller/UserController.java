package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Cliente;
import com.example.demo.service.cliente.ClienteServiceImp;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    ClienteServiceImp clienteService;

    private Cliente cliente;

    @RequestMapping("/saldo")
    public String getListLoteria(Model m) {
        this.cliente = clienteService.buscarCliente();
        System.out.println("******* Usuario *****" + cliente);
        m.addAttribute("menu", "saldo");
        m.addAttribute("cliente", this.cliente);
        return "user/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@RequestParam(name = "saldo") BigDecimal saldo, ModelAndView mav,
            RedirectAttributes attr) {

        if (saldo != null) {
            this.cliente = clienteService.buscarCliente();

            if (this.cliente.getSaldo() == null) {
                this.cliente.setSaldo(saldo);
            } else {
                this.cliente.setSaldo(saldo.add(this.cliente.getSaldo()));
            }

            System.out.println("******* Usuario *****" + cliente);

            clienteService.updateUser(cliente);
            mav.setViewName("redirect:/home");
            attr.addFlashAttribute("mensagem", cliente.getNome() + " cadastrada(o) com sucesso!");
            attr.addFlashAttribute("pessoa", cliente);
        } else {
            System.out.println("Cliente não foi salvo");
            mav.addObject("mensagem", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("redirect:/user/saldo");
        }
        return mav;
    }
}

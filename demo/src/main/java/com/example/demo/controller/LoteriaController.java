package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.service.ApostaService;
import com.example.demo.service.cliente.ClienteServiceImp;

@Controller
@RequestMapping("aposta")
public class LoteriaController {

    @Autowired
    ApostaService serviceAposta;

    @Autowired
    ClienteServiceImp clienteService;

    private Cliente cliente;

    @RequestMapping("/minhas_apostas")
    public String getListLoteria(Model m) {
        this.cliente = clienteService.buscarCliente();
        m.addAttribute("menu", "apostas");
        m.addAttribute("apostas", serviceAposta.getMinhasApostas(cliente));
        return "loteria/listar_loteria";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getApostas(Model m) {
        this.cliente = clienteService.buscarCliente();
        System.out.println(this.cliente);
        m.addAttribute("menu", "apostas");
        m.addAttribute("apostas", new Aposta());
        return "loteria/game";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addAposta(@Valid Aposta aposta, HttpSession session, ModelAndView modelAndView,
            RedirectAttributes redirectAttts) {
        try {
            this.cliente = clienteService.buscarCliente();
            serviceAposta.saveAposta(aposta, cliente);
            redirectAttts.addFlashAttribute("mensagem", "Aposta cadastrada com sucesso");
            modelAndView.setViewName("redirect:/aposta");
        } catch (Exception e) {
            redirectAttts.addFlashAttribute("mensagem", e.getMessage());
            modelAndView.setViewName("redirect:/aposta");
        }
        return modelAndView;
    }

}

package com.example.demo.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Cliente;
import com.example.demo.model.Sorteio;
import com.example.demo.model.User;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.imp.ClienteServiceImp;
import com.example.demo.service.imp.SorteioServiceImp;

@Controller
@RequestMapping("jogo")
public class SorteioController {

    @Autowired
    SorteioServiceImp sorteioService;

    @Autowired
    ClienteServiceImp clienteService;

    private Cliente cliente;

    @RequestMapping("/resource")
    public Map<String, Object> home(Principal principal) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello " + principal.getName());
        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getNewGame(Model m) {

        m.addAttribute("menu", "Jogo");
        m.addAttribute("sorteio", new Sorteio());
        return "sorteio/register";
    }

    @RequestMapping("/lista")
    public String getJogos(Model m) {

        m.addAttribute("menu", "Lista_jogo");
        m.addAttribute("sorteios", sorteioService.getSorteios());
        return "sorteio/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addAposta(@Valid Sorteio sorteio, HttpSession session, ModelAndView modelAndView,
            RedirectAttributes redirectAttts) {
        try {
            this.cliente = clienteService.buscarCliente();
            sorteioService.saveSorteio(sorteio, this.cliente);
            redirectAttts.addFlashAttribute("mensagem", "Jogo cadastrada com sucesso");
            modelAndView.setViewName("redirect:/jogo");
        } catch (Exception e) {
            redirectAttts.addFlashAttribute("mensagem", e.getMessage());
            modelAndView.setViewName("redirect:/jogo");
        }
        return modelAndView;
    }

}

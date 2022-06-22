package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Aposta;
import com.example.demo.model.Cliente;
import com.example.demo.service.aposta.ServiceAposta;

@Controller
@RequestMapping("aposta")
public class LoteriaController {

    ServiceAposta serviceAposta = new ServiceAposta();

    @RequestMapping("/minhas_apostas")
    public String getListLoteria(Model m) {
        m.addAttribute("menu", "apostas");
        return "loteria/listar_loteria";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getApostas(Model m) {
        m.addAttribute("menu", "apostas");
        m.addAttribute("apostas", new Aposta());
        return "loteria/game";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addAposta(@Valid Aposta aposta, HttpSession session, ModelAndView modelAndView,
            RedirectAttributes redirectAttts) {
        try {
            Aposta p = serviceAposta.setAposta(aposta);
            System.out.println(p);
            redirectAttts.addFlashAttribute("mensagem", "Aposta cadastrada com sucesso");
            modelAndView.setViewName("redirect:/aposta");
        } catch (Exception e) {
            redirectAttts.addFlashAttribute("mensagem", e.getMessage());
            modelAndView.setViewName("redirect:/aposta");
        }
        return modelAndView;
    }

}

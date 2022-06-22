package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Aposta;
import com.example.demo.model.Jogo;
import com.example.demo.service.aposta.ServiceAposta;

@Controller
@RequestMapping("jogo")
public class JogoController {

    ServiceAposta serviceAposta = new ServiceAposta();

    @RequestMapping("/register")
    public String getListJogo(Model m) {
        m.addAttribute("menu", "Jogo");

        return "jogo/register";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getApostas(Model m) {
        m.addAttribute("menu", "Lista_jogo");
        return "jogo/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addAposta(@Valid Jogo jogo, HttpSession session, ModelAndView modelAndView,
            RedirectAttributes redirectAttts) {
        try {
            System.out.println(jogo);
            redirectAttts.addFlashAttribute("mensagem", "Jogo cadastrada com sucesso");
            modelAndView.setViewName("redirect:/jogo");
        } catch (Exception e) {
            redirectAttts.addFlashAttribute("mensagem", e.getMessage());
            modelAndView.setViewName("redirect:/jogo");
        }
        return modelAndView;
    }

}

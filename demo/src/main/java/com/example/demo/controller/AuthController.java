package com.example.demo.controller;


import javax.servlet.http.HttpSession;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    ClienteRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView valide(Cliente cliente, HttpSession session, ModelAndView modelAndView,
            RedirectAttributes redirectAttts) {
        if ((cliente = this.isValido(cliente)) != null) {
            session.setAttribute("usuario", cliente);
            modelAndView.setViewName("redirect:/home");
        } else {
            redirectAttts.addFlashAttribute("mensagem", "Login e/ou senha inválidos!");
            modelAndView.setViewName("redirect:/auth");
        }
        return modelAndView;
    }

    private Cliente isValido(Cliente cliente) {
        Cliente clienteBD =  clienteRepository.findByUsername(cliente.getUsername());
        boolean valido = false;
        if (clienteBD != null) {
            if (PasswordUtil.checkPass(cliente.getSenha(), clienteBD.getSenha())) {
                valido = true;
            }
        }
        return valido ? clienteBD : null;
    }

}

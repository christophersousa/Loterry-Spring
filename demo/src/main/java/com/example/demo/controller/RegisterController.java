package com.example.demo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    ClienteRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("auth/register");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ModelAndView save(@Valid Cliente cliente, BindingResult validation, ModelAndView mav,
            RedirectAttributes attr) {

        if (!validation.hasErrors()) {
            cliente.setSenha(PasswordUtil.hashPassword(cliente.getSenha()));
            clienteRepository.save(cliente);
            mav.setViewName("redirect:/auth");
            attr.addFlashAttribute("mensagem", cliente.getNome() + " cadastrada(o) com sucesso!");
            attr.addFlashAttribute("pessoa", cliente);
        } else {
            System.out.println("Cliente não foi salvo");
            mav.addObject("mensagem", "Erros de validação! Corrija-os e tente novamente.");
            mav.setViewName("redirect:/auth");
        }
        return mav;
    }
}

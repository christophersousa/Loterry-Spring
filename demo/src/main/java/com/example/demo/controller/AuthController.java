package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import com.example.demo.model.Cliente;
// import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.UserRepository;
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
    UserRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("cliente", new Cliente());
        return modelAndView;
    }

}

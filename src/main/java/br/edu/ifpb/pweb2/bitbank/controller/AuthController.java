package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CorrentistaService correntistaService;

    @GetMapping("/login")
     public String mostrarFormularioLogin(Correntista correntista, Model model) {
        model.addAttribute("correntista", correntista);
        return "auth/login";
    }


    @PostMapping("/login")
    public String fazerLogin(Correntista correntista, HttpSession session, RedirectAttributes flash) {
        
        Correntista usuario = correntistaService.findByEmail(correntista.getEmail());

        if (usuario != null && usuario.getSenha().equals(correntista.getSenha())) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/home";
        } else {
            flash.addFlashAttribute("erro", "Email ou senha inválidos");
            return "redirect:/auth/login";

        }
    }


}

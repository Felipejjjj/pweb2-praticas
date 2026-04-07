package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaRepository correntistaRepository;


    @RequestMapping("/save")
    public String save(Correntista correntista, Model model){
        
        //Nome obrigatório
        if (correntista.getNome() == null || correntista.getNome().trim().isEmpty()) {
            model.addAttribute("mensagem", "Nome é obrigatório!");
            model.addAttribute("correntista", correntista);
            return "correntistas/form";
        }

        //Nome máximo 50 caracteres
        if (correntista.getNome().length() > 50) {
            model.addAttribute("mensagem", "Nome deve ter no máximo 50 caracteres!");
            model.addAttribute("correntista", correntista);
            return "correntistas/form";
        }

        //Email obrigatório
        if (correntista.getEmail() == null || correntista.getEmail().trim().isEmpty()) {
            model.addAttribute("mensagem", "Email é obrigatório!");
            model.addAttribute("correntista", correntista);
            return "correntistas/form";
        }

        // c) Senha obrigatória
        if (correntista.getSenha() == null || correntista.getSenha().trim().isEmpty()) {
            model.addAttribute("mensagem", "Senha é obrigatória!");
            model.addAttribute("correntista", correntista);
            return "correntistas/form";
        }

        // ✅ Se passou nas validações → salva
        correntistaRepository.save(correntista);

        model.addAttribute("mensagem", "Correntista salvo com sucesso!");
        model.addAttribute("correntistas", correntistaRepository.findAll());

        return "correntistas/list";
    }
}

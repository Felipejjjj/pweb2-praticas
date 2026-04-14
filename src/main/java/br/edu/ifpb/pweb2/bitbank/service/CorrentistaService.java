package br.edu.ifpb.pweb2.bitbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.util.PasswordUtil;

@Component
public class CorrentistaService implements Service<Correntista, Integer> {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Override
    public List<Correntista> findAll(){

        return correntistaRepository.findAll();
    }

    @Override
    public Correntista findById(Integer id) {
        return correntistaRepository.findById(id);
    }

    @Override
    public Correntista save(Correntista c) {
        c.setSenha(PasswordUtil.hashPassword(c.getSenha()));
        return correntistaRepository.save(c);
    }

    public Correntista findByEmail(String email) {
        return correntistaRepository.findByEmail(email);
    }

    

}

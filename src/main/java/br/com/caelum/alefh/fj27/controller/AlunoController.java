package br.com.caelum.alefh.fj27.controller;

import br.com.caelum.alefh.fj27.dao.AlunoDao;
import br.com.caelum.alefh.fj27.model.Aluno;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired private AlunoDao dao;
    @Autowired private RabbitTemplate rabbitTemplate;
    @PostMapping
    public void cadastrar(@RequestBody Aluno aluno) {
        dao.save(aluno);
        rabbitTemplate.convertAndSend("topico-eventos"
        , "evento.aluno", aluno);
    }

    @GetMapping({"idade"})
    public Aluno findBy(@PathVariable("idade") Integer idade) {
        return dao.findByIdade(idade);
    }

    @GetMapping
    public List<Aluno> findAll() {
        return dao.findAll();
    }
}

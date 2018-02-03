package br.com.caelum.alefh.fj27.dao;

import br.com.caelum.alefh.fj27.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoDao
extends MongoRepository<Aluno, String>{
    Aluno findByIdade(Integer idade);
}

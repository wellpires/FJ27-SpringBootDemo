package br.com.caelum.alefh.fj27.consumer;

import br.com.caelum.alefh.fj27.model.Aluno;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AlunoConsumer {

    @RabbitListener(queues = "queue.alunos")
    public void leMensagem(Aluno aluno) {
        System.out.println("[Consumer Alunos] " +
                "recebendo mensaagem: " + aluno);
    }

}

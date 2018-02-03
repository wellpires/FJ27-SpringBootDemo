package br.com.caelum.alefh.fj27.conf;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Value("${rabbitmq.filas.alunos}")
    private String NOME_FILA_ALUNOS;
    @Value("${rabbitmq.topico}")
    private String NOME_TOPICO;
    @Value("${rabbitmq.rt.alunos}")
    private String RT_ALUNOS;


    @Bean
    public RabbitTemplate rabbitTemplate(MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Binding bindTopicoEventosComFilaAlunos(Queue queueAlunos,
                                                  TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queueAlunos)
                .to(topicExchange())
                .with(RT_ALUNOS);
    }

    @Bean
    public Queue queueAlunos() {
        return new Queue(NOME_FILA_ALUNOS);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(NOME_TOPICO);
    }
}

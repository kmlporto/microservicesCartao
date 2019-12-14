package br.com.microservice.analiseCartao.service;

import br.com.microservice.analiseCartao.web.dto.CartaoRequest;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Value("analiseCartao")
    private String FILA_ANALISE_CARTAO;

    public Boolean enviarCartao(CartaoRequest cartaoRequest) {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        //localizacao do gestor da fila (Queue Manager)
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        try {
            Connection connection = connectionFactory.newConnection();

            Channel channel = connection.createChannel();

            Gson gson = new Gson();
            String mensagem = gson.toJson(cartaoRequest);

            String fila = FILA_ANALISE_CARTAO;
            channel.queueDeclare(fila, true, false, false, null);
            channel.basicPublish("", fila, null, mensagem.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

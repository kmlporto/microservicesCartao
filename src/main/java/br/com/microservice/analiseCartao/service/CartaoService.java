package br.com.microservice.analiseCartao.service;

import br.com.microservice.analiseCartao.web.dto.CartaoRequest;
import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    public Boolean enviarCartao(CartaoRequest cartaoRequest) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        String EXCHANGE_NAME = "embossing";

        //localizacao do gestor da fila (Queue Manager)
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        try {
            Connection connection = connectionFactory.newConnection();

            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            Gson gson = new Gson();
            String mensagem = gson.toJson(cartaoRequest);
            channel.basicPublish(EXCHANGE_NAME, "", null, mensagem.getBytes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

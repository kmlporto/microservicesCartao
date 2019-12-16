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
        String ROUTING_KEY = cartaoRequest.getEntregaLoja() ? "processing": "send";
        BuiltinExchangeType EXCHANGE_TYPE = BuiltinExchangeType.DIRECT;
        //localizacao do gestor da fila (Queue Manager)
        connectionFactory.setHost("rabbitmq");
        connectionFactory.setPort(5672);
        try {
            Connection connection = connectionFactory.newConnection();

            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            Gson gson = new Gson();
            String mensagem = gson.toJson(cartaoRequest);
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, mensagem.getBytes());
            System.out.println("Produção realizada na ROUTING_KEY " + ROUTING_KEY);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

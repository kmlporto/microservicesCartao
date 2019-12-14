package br.com.microservice.analiseCartao.web.resource;

import lombok.Data;

@Data
public class CartaoResponse {

    private String message;

    public CartaoResponse(String message) {
        this.message = message;
    }
}

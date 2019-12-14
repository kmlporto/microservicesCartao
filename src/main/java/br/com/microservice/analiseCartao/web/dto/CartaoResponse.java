package br.com.microservice.analiseCartao.web.dto;

import lombok.Data;

@Data
public class CartaoResponse {

    private String message;

    public CartaoResponse(String message) {
        this.message = message;
    }
}

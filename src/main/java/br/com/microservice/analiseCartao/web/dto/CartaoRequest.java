package br.com.microservice.analiseCartao.web.dto;

import lombok.Data;

@Data
public class CartaoRequest {

    private String numero;
    private String nomePortador;
    private String validade;
    private String codSeguranca;

}

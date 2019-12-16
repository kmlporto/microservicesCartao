package br.com.microservice.analiseCartao.web.dto;

import lombok.Data;

@Data
public class CartaoRequest {

    private String numero;
    private String nomePortador;
    private String validade;
    private String codSeguranca;
    private boolean entregaLoja;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomePortador() {
        return nomePortador;
    }

    public void setNomePortador(String nomePortador) {
        this.nomePortador = nomePortador;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(String codSeguranca) {
        this.codSeguranca = codSeguranca;
    }

    public boolean getEntregaLoja() {
        return entregaLoja;
    }

    public void setEntregaLoja(boolean entregaLoja) {
        this.entregaLoja = entregaLoja;
    }

}

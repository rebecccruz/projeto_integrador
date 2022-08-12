package br.com.dh.meli.projeto_integrador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CustomerDTO {
    @NotNull(message = "Nome do cliente é obrigatório")
    @JsonProperty("costumer_name")
    public String name;
    @NotNull
    public Long shoppingCartId;
}

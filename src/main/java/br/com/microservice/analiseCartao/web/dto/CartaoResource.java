package br.com.microservice.analiseCartao.web.dto;

import br.com.microservice.analiseCartao.web.resource.CartaoResponse;
import br.com.microservice.analiseCartao.service.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cartoes")
public class CartaoResource {

    private final CartaoService cartaoService;

    public CartaoResource(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<CartaoResponse> cadastrarCartao(@RequestBody CartaoRequest cartaoRequest) {
        cartaoService.enviarCartao(cartaoRequest);
        return ResponseEntity.ok().body(new CartaoResponse("Cartão cadastrado."));
    }

}

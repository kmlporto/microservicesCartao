package br.com.microservice.analiseCartao.web.resource;

import br.com.microservice.analiseCartao.service.CartaoService;
import br.com.microservice.analiseCartao.web.dto.CartaoRequest;
import br.com.microservice.analiseCartao.web.dto.CartaoResponse;
import org.springframework.http.HttpStatus;
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
        boolean result = cartaoService.enviarCartao(cartaoRequest);
        if (result)
            return ResponseEntity.ok().body(new CartaoResponse("Cartão cadastrado."));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartaoResponse("Erro ao processar informação para Embossing."));
    }

}

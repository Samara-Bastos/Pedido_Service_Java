package com.pedido.service.project.api;


import com.pedido.service.project.domain.model.Pedido;
import com.pedido.service.project.infrastructure.producer.PedidoProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoProducer pedidoProducer;

    public PedidoController(PedidoProducer pedidoProducer) {
        this.pedidoProducer = pedidoProducer;
    }

    @PostMapping
    public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
        pedidoProducer.enviarPedido(pedido);
        return ResponseEntity.ok("Pedido enviado com sucesso!");
    }
}
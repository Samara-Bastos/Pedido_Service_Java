package com.pedido.service.project.infrastructure.producer;

import com.pedido.service.project.domain.model.Pedido;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;


@Component
public class PedidoProducer {
    private final KafkaTemplate<String, Pedido> kafkaTemplate;

    public PedidoProducer(KafkaTemplate<String, Pedido> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarPedido(Pedido pedido) {
        kafkaTemplate.send("novos-pedidos", pedido);
    }
}

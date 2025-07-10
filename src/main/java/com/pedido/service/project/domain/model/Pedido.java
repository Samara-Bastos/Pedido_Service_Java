package com.pedido.service.project.domain.model;

import lombok.Data;

@Data
public class Pedido {
    private Long id;
    private String cliente;
    private Double valor;
}

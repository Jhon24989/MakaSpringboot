package com.cafemaka.system.model.dto;

import java.util.List;

public class CompraDTO {
    private Long clienteId;
    private List<ItemCompraDTO> items;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<ItemCompraDTO> getItems() { return items; }
    public void setItems(List<ItemCompraDTO> items) { this.items = items; }
}

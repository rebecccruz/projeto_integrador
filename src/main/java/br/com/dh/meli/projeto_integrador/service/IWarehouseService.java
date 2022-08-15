package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.WarehouseStocksDTO;
import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Warehouse;

public interface IWarehouseService {
    Warehouse findWarehouseById(Long id);
    Warehouse findWarehouseByCode(String code);
    Representant findRepresentantFromWarehouse(Warehouse warehouse, Long id);
    WarehouseStocksDTO findStocksByProductId(String productId);
}

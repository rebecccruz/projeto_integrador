package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import java.util.*;

public interface IWarehouseService {
    Warehouse findWarehouseByCode(String code);
    Representant findRepresentantFromWarehouse(Warehouse warehouse, Long id);
}

package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.model.Representant;
import br.com.dh.meli.projeto_integrador.model.Section;
import br.com.dh.meli.projeto_integrador.model.Warehouse;
import java.util.*;

public interface IWarehouseService {
    Warehouse findWarehouseById(Long id);
    Warehouse findWarehouseByCode(String code);
    Section findSectionByCode(Warehouse warehouse, String code);
    Representant findRepresentantFromWarehouse(Warehouse warehouse, Long id);
}

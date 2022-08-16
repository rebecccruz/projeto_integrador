package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.model.Seller;

public interface ISellerService {
    Seller createSeller(SellerDTO dto);
    Seller findSellerById(Long id);
    Seller saveSeller(Seller seller);
    Seller updateSeller(Seller seller);
    Boolean deleteSeller(Seller seller);

}

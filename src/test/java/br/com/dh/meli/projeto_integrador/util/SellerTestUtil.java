package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.model.Seller;

/**
 * Seller Test Util
 *
 * @author Alexandre Borges Souza
 * @since 15/08/2022
 */
public class SellerTestUtil {

    /**
     * Generate Seller with content
     *
     * @return Seller
     * @author Alexandre Borges Souza
     */
    public static Seller sellerGenerator() {
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setName("Larissa");
        return seller;
    }

    public static SellerDTO buildSellerDTO(Long id, String name) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(id);
        sellerDTO.setName(name);
        return sellerDTO;
    }
}

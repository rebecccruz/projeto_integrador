package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.dto.SellerDTO;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Seller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Advertisement Test Util
 *
 * @author Larissa Navarro
 */
public class AdvertisementUtil {

    /**
     * Generate Advertisement with content
     *
     * @return Advertisement
     * @author Larissa Navarro
     */
    public static Advertisement advertisementGenerator() {
        Advertisement prod = new Advertisement();
        prod.setId(1L);
        prod.setProductId("Teste 1");
        prod.setPrice(12.0);
        prod.setSeller(SellerTestUtil.sellerGenerator());
        prod.setDescription("Teste");
        return prod;
    }

    /**
     * Generate AdvertisementDTO with content
     *
     * @return AdvertisementDTO
     * @author Larissa Navarro
     */
    public static AdvertisementDTO advertisementDTO(){
        SellerDTO sellerDTO = SellerDTO.builder().build();
        sellerDTO.setId(SellerTestUtil.sellerGenerator().getId());
        sellerDTO.setName(SellerTestUtil.sellerGenerator().getName());
        AdvertisementDTO dto = new AdvertisementDTO();
        dto.setId(1L);
        dto.setProductId("Teste 1");
        dto.setPrice(500.0);
        dto.setDescription("acucar");
        dto.setSeller(sellerDTO);
        return dto;
    }

    /**
     * Generate BatchStock with content
     *
     * @return BatchStock
     * @author Larissa Navarro
     */
    public static BatchStock batchStockGenerator() {
        BatchStock batchStock = new BatchStock();
        batchStock.setBatchNumber(1);
        batchStock.setProductId("Teste 1");
        batchStock.setCurrentTemperature(10.0f);
        batchStock.setMinimumTemperature(14.0f);
        batchStock.setInitialQuantity(5);
        batchStock.setCurrentQuantity(5);
        batchStock.setManufacturingDate(LocalDate.of(2022, 8, 1));
        batchStock.setManufacturingTime(LocalDateTime.of(2022, 8, 1, 10, 30));
        batchStock.setDueDate(LocalDate.of(2022, 8, 31));
        return batchStock;
    }

    /**
     * Generate list of Advertisement
     *
     * @return List<Advertisement>
     * @author Larissa Navarro
     */
    public static List<Advertisement> getAllAdvertisement(){
        List<Advertisement> adList = new ArrayList<>();
        BatchStock batchStock = batchStockGenerator();
        batchStock.setBatchNumber(2);
        Seller seller = SellerTestUtil.sellerGenerator();
        seller.setId(2L);
        Advertisement ad2 = advertisementGenerator();
        Advertisement ad3 = advertisementGenerator();
        ad2.setId(2L);
        ad2.setSeller(seller);
        adList.add(ad2);
        batchStock.setBatchNumber(3);
        seller.setId(3L);
        ad3.setId(3L);
        ad3.setSeller(seller);
        adList.add(ad3);
        return adList;
    }

    public static Advertisement findById(Long id){
        List<Advertisement> list = getAllAdvertisement().stream()
                .filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(list.size() > 0){
            return list.get(0);
        }
        throw new NotFoundException("Id not found");
    }
}

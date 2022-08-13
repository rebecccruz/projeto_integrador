package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.enums.Category;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.model.Advertisement;
import br.com.dh.meli.projeto_integrador.model.BatchStock;
import br.com.dh.meli.projeto_integrador.model.Seller;
import br.com.dh.meli.projeto_integrador.repository.IAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdvertisementUtil {
    @Autowired
    IAdvertisementRepository repo;

    public static Advertisement advertisementGenerator() {
        return null;
//        Advertisement prod = new Advertisement();
//        prod.setId(1L);
//        prod.setBatchStock(batchStockGenerator());
//        prod.setPrice(12.0);
//        prod.setSeller(sellerGenerator());
//        prod.setDescription("Teste");
//        return prod;
    }
    public static AdvertisementDTO advertisementDTO(){
        return null;
//        AdvertisementDTO dto = new AdvertisementDTO();
//        dto.setPrice(500.0);
//        dto.setBatchStockId(1L);
//        dto.setSellerId(1L);
//        dto.setDescription("acucar");
//
//
//        return dto;
    }


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

    public static Seller sellerGenerator() {
        Seller seller = new Seller();
        seller.setId(1L);
        seller.setName("Larissa");
        return seller;
    }

    public static List<Advertisement> getAllAdvertisement(){
        return null;
//        List<Advertisement> adList = new ArrayList<>();
//        BatchStock batchStock = batchStockGenerator();
//        batchStock.setBatchNumber(2);
//        Seller seller = sellerGenerator();
//        seller.setId(2L);
//        Advertisement ad2 = advertisementGenerator();
//        Advertisement ad3 = advertisementGenerator();
//        ad2.setId(2L);
//        ad2.setBatchStock(batchStock);
//        ad2.setSeller(seller);
//        adList.add(ad2);
//
//        batchStock.setBatchNumber(3);
//        seller.setId(3L);
//        ad3.setId(3L);
//        ad3.setBatchStock(batchStock);
//        ad3.setSeller(seller);
//        adList.add(ad3);
//        return adList;
    }

    public static Advertisement findById(Long id){
        List<Advertisement> list = getAllAdvertisement().stream()
                .filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(list.size() > 0){
            return list.get(0);
        }
        throw new NotFoundException("Id not found");
    }

    public static Advertisement findByCategory(String category){
        return null;
//        Advertisement ad = new Advertisement();
//        Set<Category> categories = Collections.singleton(ad.getBatchStock().getSection().getCategory());
//        return ad;
    }
}

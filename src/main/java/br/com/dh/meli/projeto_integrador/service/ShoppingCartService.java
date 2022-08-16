package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.AdvertisementDTO;
import br.com.dh.meli.projeto_integrador.dto.BatchStockDTO;
import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.mapper.*;
import br.com.dh.meli.projeto_integrador.model.*;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.ICustomerRepository;
import br.com.dh.meli.projeto_integrador.repository.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private IShoppingCartRepository repo;

    @Autowired
    private ICustomerService customerService;


    @Autowired
    private IAdvertisementService advertisementService;

    @Autowired
    private IBatchStockService batchStockService;

    @Autowired
    private IItemService itemService;

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        Optional<ShoppingCart> shoppingCart = repo.findById(id);
        if (shoppingCart.isEmpty()) {
            throw new NotFoundException("The shopping cart doesn't exist");
        }
        return shoppingCart.get();
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCartDTO dto) {
        ShoppingCart shoppingCart = convertToModel(dto);
        List<Item> items = new ArrayList<>();
        dto.getItems().stream().forEach(itemDTO -> {
            Item item = itemService.createItem(itemDTO, shoppingCart);
            if(shoppingCart.getStatus().equals(Status.FECHADO)){
                reserveBatchStockByItem(item);
                itemService.save(item);
            }
            items.add(item);
        });
       shoppingCart.setItems(items);
        return shoppingCart;
    }

    private void reserveBatchStockByItem(Item item) {
        List<BatchStock> batchStocks = batchStockService.findAllByProductId(item.getAdvertisement().getProductId());
        batchStocks.stream().forEach(b -> {
            if (item.isBatchStockIsEmpty()) {
                if (verifyDueDate(b.getDueDate()) && hasEnoughBatchQuantity(b.getCurrentQuantity(), item.getQuantity())) {
                    item.setBatchStock(b);
                    decreaseQuantity(item);
                }
            }
        });
        if (item.isBatchStockIsEmpty()) {
            throw new NotFoundException("No stock available");
        }
    }

    private boolean verifyDueDate(LocalDate date) {
        long differenceData = DAYS.between(LocalDate.now(), date);
        return differenceData >= 21;
    }

    private void decreaseQuantity( Item item){
        Integer quantity = item.getQuantity();
        Integer stockQuantity = item.getBatchStock().getCurrentQuantity();
        if(stockQuantity >= quantity) {
           batchStockService.decreaseQuantity(item.getBatchStock(),quantity);
        }
    }

    private boolean hasEnoughBatchQuantity(Integer batchQuantity, Integer shopQuantity) {
        return batchQuantity >= shopQuantity;
    }

    @Override
    public ShoppingCart updateShoppingCart(Long shoppingCartId, Status status) {
        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
        shoppingCart.setStatus(status);
        if(shoppingCart.getStatus().equals(Status.FECHADO)) {
            shoppingCart.getItems().forEach(item -> {
                reserveBatchStockByItem(item);
                itemService.save(item);
            });
        }
        return repo.save(shoppingCart);
    }

    public ShoppingCartDTO convertToDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO dto = IShoppingCartMapper.MAPPER.shoppingCartToDTO(shoppingCart);
        dto.setCustomerId(shoppingCart.getCustomer().getId());
        List<ItemDTO> itemsDTO = new ArrayList<>();
        shoppingCart.getItems().forEach(i -> {
            itemsDTO.add(itemService.convertToDTO(i));
        });
        dto.setItems(itemsDTO);
        return dto;
    }

    private ShoppingCart convertToModel(ShoppingCartDTO dto) {
        ShoppingCart shoppingCart = IShoppingCartMapper.MAPPER.shoppingCartDTOToModel(dto);
        Customer customer = customerService.getCustomerById(dto.getCustomerId());
        shoppingCart.setCustomer(customer);
        shoppingCart.setStatus(dto.getStatus());
        repo.save(shoppingCart);
        customer.setShoppingCart(shoppingCart);
        return shoppingCart;
    }
}

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
    private ICustomerRepository customerRepository;

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
        List<Item> items = new ArrayList<>();
        dto.getItems().stream().forEach(itemDTO -> {
            Item item = itemService.createItem(itemDTO);
            reserveBatchStockByItem(item);
            items.add(item);
        });
        return shoppingCartMapper(dto, items);
    }

    private void reserveBatchStockByItem(Item item) {
        List<BatchStock> batchStocks = batchStockService.findAllByProductId(item.getAdvertisement().getProductId());
        batchStocks.stream().forEach(b -> {
            if (verifyDueDate(b.getDueDate()) && hasEnoughBatchQuantity(b.getCurrentQuantity(), item.getQuantity())) {
                if (item.isBatchStockIsEmpty()) {
                    item.setBatchStock(b);
                }
            }
        });
        if (item.isBatchStockIsEmpty()) {
            throw new NotFoundException("No stock available");
        }
    }

    private boolean verifyDueDate(LocalDate date) {
        long differenceData = DAYS.between(LocalDate.now(), date);
        if (differenceData < 21) {
            return false;
        }
        return true;
    }

//    private boolean decreaseQuantity(Status status, Item item){
//        if(status.equals(Status.ABERTO)){
//            item.getQuantity()
//        }
//    }

    private boolean hasEnoughBatchQuantity(Integer batchQuantity, Integer shopQuantity) {
        if (batchQuantity < shopQuantity) {
            return false;
        }
        return true;
    }

    @Override
    public ShoppingCart updateShoppingCart(Long shoppingCartId, Status status) {
        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
        shoppingCart.setStatus(status);
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

    private ShoppingCart shoppingCartMapper(ShoppingCartDTO dto, List<Item> items) {
        ShoppingCart shoppingCart = IShoppingCartMapper.MAPPER.shoppingCartDTOToModel(dto);
        shoppingCart.setItems(items);
        Customer customer = customerRepository.findById(dto.getCustomerId()).get();
        shoppingCart.setCustomer(customer);
        shoppingCart.setStatus(dto.getStatus());
        repo.save(shoppingCart);
        customer.setShoppingCart(shoppingCart);
        return shoppingCart;
    }
}
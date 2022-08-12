package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.exception.NotFoundException;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.mapper.IItemMapper;
import br.com.dh.meli.projeto_integrador.mapper.IShoppingCartMapper;
import br.com.dh.meli.projeto_integrador.model.*;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.ICustomerRepository;
import br.com.dh.meli.projeto_integrador.repository.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Override
    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartdto) {
//        Advertisement advertisement = advertisementService.getAdvertisementById();
        List<Item> items = new ArrayList<>();
        shoppingCartdto.getItems().forEach( idto -> {
            Item item = IItemMapper.MAPPER.mappingItemDTOItem(idto);
            Advertisement advertisement = advertisementService.getAdvertisementById(idto.getAdvertisementId());
            List<BatchStock> batchStocks = batchStockService.findAllByProductId(advertisement.getProductId());
            batchStocks.stream().forEach(b ->{
                if(item.getBatchStock() == null){
                    if(b.getCurrentQuantity() >= idto.getQuantity()){
                        item.setBatchStock(b);

                    }
                }
            });
            if(item.getBatchStock() == null){
                throw new NotFoundException("Not found stock");
            }
            item.setAdvertisement(advertisement);
            items.add(item);
        });
        ShoppingCart shoppingCart = shoppingCartMapper(shoppingCartdto, items);
        return shoppingCart;
    }


    @Override
    public ShoppingCart updateShoppingCart(ShoppingCartDTO shoppingCartdto, long shoppingCartId) {
        try{
            Optional<ShoppingCart> getShoppingCart = repo.findById(shoppingCartId);
            ShoppingCart shoppingCart = IShoppingCartMapper.MAPPER.shoppingCartDTOToModel(shoppingCartdto);
            if(getShoppingCart.isPresent()){
                return repo.save(shoppingCart);
            }
            throw new BadRequestException("The shopping cart doesn't exist");
        }
        catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    public ShoppingCartDTO convertToDTO(ShoppingCart shoppingCart){
        ShoppingCartDTO dto = IShoppingCartMapper.MAPPER.shoppingCartToDTO(shoppingCart);
        dto.setCustomerId(shoppingCart.getCustomer().getId());
        List<ItemDTO> itemsDTO = new ArrayList<>();
        shoppingCart.getItems().forEach( i ->{
            ItemDTO dtoItem = IItemMapper.MAPPER.mappingItemToItemDTO(i);
            dtoItem.setAdvertisementId(i.getAdvertisement().getId());
            dtoItem.setBatchStockId(i.getBatchStock().getBatchNumber());
            itemsDTO.add(dtoItem);
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

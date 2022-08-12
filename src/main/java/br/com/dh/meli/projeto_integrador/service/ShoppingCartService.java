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
    @Override
    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartdto) {
//
//      Advertisement advertisement = advertisementService.getAdvertisementById();

        //verificar os relacionamentos das outras entidades, se existem, salvar no banco de dados.
        //persistir ShoppingCart

        //persistir carrinho

        //adver
        //1. No item dizer a qual carrinho pertence,
        //2. Verificar a qual anuncio precisa ser vinculado,
        //List<Item> itemTeste = IItemMapper.MAPPER.map(shoppingCartdto.getItems());


        List<Item> items = new ArrayList<>();
        shoppingCartdto.getItems().stream().forEach( idto -> {
            Item item = IItemMapper.MAPPER.mappingItemDTOItem(idto);
//            Item item = new Item();
//            item.setBatchStock(idto.getBatchStock());
//            item.setAdvertisement(idto.getAdvertisement());
//            item.setQuantity(2);
//            items.add(item);
            Advertisement advertisement = advertisementService.getAdvertisementById(idto.getAdvertisement().getId());
            List<BatchStock> batchStocks = batchStockService.findAllByProductId(advertisement.getProductId());



            batchStocks.stream().forEach(b ->{
                if(item.getBatchStock() == null){
                   hasEnoughBatchQuantity(b.getCurrentQuantity(), idto.getQuantity());
                   item.setBatchStock(b);
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

    private void verifyDueDate(LocalDate date){
        long differenceData =  DAYS.between(date,LocalDate.now());
        if(differenceData < 21){
            throw new BadRequestException("dueData isn't valid");
        }
    }

    private void hasEnoughBatchQuantity(Integer batchQuantity,Integer shopQuantity){
        if(batchQuantity < shopQuantity){
            throw new BadRequestException("Not enough batch quantity");
        }
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
            dtoItem.setAdvertisement(i.getAdvertisement());
            dtoItem.setBatchStock(i.getBatchStock());
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

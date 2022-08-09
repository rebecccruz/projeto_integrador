package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.exception.BadRequestException;
import br.com.dh.meli.projeto_integrador.mapper.ICountryMapper;
import br.com.dh.meli.projeto_integrador.mapper.IShoppingCartMapper;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.model.geolocalization.CountryModel;
import br.com.dh.meli.projeto_integrador.repository.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private IShoppingCartRepository repo;
    @Override
    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartdto) {

       try{
           ShoppingCart shoppingCart = IShoppingCartMapper.MAPPER.shoppingCartDTOtoModel(shoppingCartdto);
           return repo.save(shoppingCart);
       }
       catch(Exception e){
           throw new BadRequestException(e.getMessage());
       }
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCartDTO shoppingCartdto, long shoppingCartId) {
        try{
            Optional<ShoppingCart> getShoppingCart = repo.findById(shoppingCartId);
            ShoppingCart shoppingCart = IShoppingCartMapper.MAPPER.shoppingCartDTOtoModel(shoppingCartdto);
            if(getShoppingCart.isPresent()){
                return repo.save(shoppingCart);
            }
            throw new BadRequestException("The shopping cart doesn't exist");
        }
        catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}

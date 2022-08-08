package br.com.dh.meli.projeto_integrador.service;

import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;
import br.com.dh.meli.projeto_integrador.repository.IShoppingCartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private IShoppingCartRepository repo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartdto) {

        ShoppingCart shoppingCart = mapper.map(shoppingCartdto,ShoppingCart.class);

        return repo.save(shoppingCart);
    }

    @Override
    public ShoppingCartDTO updateShoppingCart(ShoppingCart shoppingCart) {

    }
}

package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.dto.ShoppingCartDTO;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.model.Customer;
import br.com.dh.meli.projeto_integrador.model.Item;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartUtil {

    /**
     * Method that helps to create a static shopping cart object to be used on Shopping Cart payload
     * @author Evelyn Cristini
     * @return returns a static shoppingCart
     */
    public static ShoppingCart shoppingCartGenerator () {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<Item> item = new ArrayList<>();
        item.add(ItemUtil.completeItem(shoppingCart));

        shoppingCart.setId(1L);
        shoppingCart.setItems(item);
        shoppingCart.setStatus(Status.ABERTO);
        shoppingCart.setCustomer(customerGenerator());

        return shoppingCart;
    }

    /**
     * Method that helps to create a static shopping cart dto to be used on Shopping Cart payload
     * @author Evelyn Cristini
     * @return returns dto
     */
    public static ShoppingCartDTO shoppingCartDTOGenerator () {
        ShoppingCartDTO dto = new ShoppingCartDTO();
        List<ItemDTO> itemDto = new ArrayList<>();
        itemDto.add(ItemUtil.emptyItemDTO());

        dto.setItems(itemDto);
        dto.setStatus(Status.ABERTO);
        dto.setCustomerId(1L);

        return dto;
    }

    /**
     * Method that helps to create a static customer to be used on Shopping Cart payload
     * @author Evelyn Cristini
     * @return returns customer
     */
    public static Customer customerGenerator() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Evelyn");
        return customer;
    }

}

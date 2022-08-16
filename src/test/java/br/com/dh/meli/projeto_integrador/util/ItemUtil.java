package br.com.dh.meli.projeto_integrador.util;

import br.com.dh.meli.projeto_integrador.dto.ItemDTO;
import br.com.dh.meli.projeto_integrador.enums.Status;
import br.com.dh.meli.projeto_integrador.model.Item;
import br.com.dh.meli.projeto_integrador.model.ShoppingCart;

public class ItemUtil {

    /**
     * Method that helps to create a static item object order number id to be used on Item payload
     * @author Evelyn Cristini
     * @return returns a static item
     */
    public static Item emptyItem() {
        Item item = new Item();
        item.setId(1L);
        item.setQuantity(1);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setStatus(Status.FECHADO);
        return item;
    }

    public static Item completeItem(ShoppingCart cart) {
        Item item = emptyItem();
        item.setAdvertisement(AdvertisementUtil.advertisementGenerator());
        item.setBatchStock(BatchStocksTestUtil.listOfBatchStock().stream().findFirst().get());
        item.setShoppingCart(cart);
        return item;
    }

    public static Item completeItem() {
        return completeItem(ShoppingCartUtil.shoppingCartGenerator());
    }

    /**
     * Method that helps to create a static empty item DTO to be used on Item payload
     * @author Evelyn Cristini
     * @return returns a dto
     */
    public static ItemDTO emptyItemDTO() {
        ItemDTO dto = new ItemDTO();
        dto.setQuantity(1);
        dto.setAdvertisementId(1L);
        dto.setBatchNumber(1);
        return dto;
    }
}

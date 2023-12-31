package com.example.kakaoshop.order.item;

import com.example.kakaoshop.cart.response.CartItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductDTO {
    private String productName;
    private List<OrderItemDTO> orderItems;

    @Builder
    public ProductDTO(String productName, List<OrderItemDTO> orderItems) {
        this.productName = productName;
        this.orderItems = orderItems;
    }
}

package com.example.shoppingmall.order;

import com.example.shoppingmall.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    int id;
    Product product;
    int count;

    public Order(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}

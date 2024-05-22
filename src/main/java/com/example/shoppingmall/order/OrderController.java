package com.example.shoppingmall.order;

import com.example.shoppingmall.product.Product;
import com.example.shoppingmall.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {
    OrderService orderService;
    ProductService productService;
    @PostMapping("/orders")
    public void orderProduct(@RequestBody OrderDTO orderDTO){
        Product orderedProduct = productService.findProduct(orderDTO.getProductId());
        // TODO : Service로 이사갈게요. DTO -> Entity
        Order requestOrder = new Order(
                orderedProduct, orderDTO.getCount()
        );
        // setter 방법
        // Order requestOrder = new Order();
//        requestOrder.setProduct(orderedProduct);
//        requestOrder.setCount(orderDTO.getCount());


        orderService.orderProduct(requestOrder);
    }
}

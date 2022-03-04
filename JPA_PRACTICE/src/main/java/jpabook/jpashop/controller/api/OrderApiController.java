package jpabook.jpashop.controller.api;

import jpabook.jpashop.controller.api.dto.OrderResponses;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v2/orders")
    public List<OrderResponses.OrderResponse> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream().map(OrderResponses.OrderResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/api/v3/orders")
    public List<OrderResponses.OrderResponse> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();
        for (Order order : orders) {
            System.out.println("order = " + order + " , id = " + order.getId());
        }
        return orders.stream().map(OrderResponses.OrderResponse::new).collect(Collectors.toList());
    }
}

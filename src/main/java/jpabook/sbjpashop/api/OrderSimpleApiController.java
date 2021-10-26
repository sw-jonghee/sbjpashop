package jpabook.sbjpashop.api;

import jpabook.sbjpashop.domain.Order;
import jpabook.sbjpashop.repository.OrderRepository;
import jpabook.sbjpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne
 * Order
 * Order -> Member
 * Order -> Delivery
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    /**
     * xxxxx 절대!! 이렇게 하지 말기
     * */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for(Order order : all) {
            order.getMember().getName();        //lazy 강제 초기화 -- 이렇게 하면 안돼!! 성능이슈
            order.getDelivery().getAddress();   //lazy 강제 초기화 -- 이렇게 하면 안돼!! 성능이슈
        }

        return all;
    }
}

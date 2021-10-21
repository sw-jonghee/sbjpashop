package jpabook.sbjpashop.service;

import jpabook.sbjpashop.domain.Address;
import jpabook.sbjpashop.domain.Member;
import jpabook.sbjpashop.domain.Order;
import jpabook.sbjpashop.domain.OrderStatus;
import jpabook.sbjpashop.domain.item.Book;
import jpabook.sbjpashop.domain.item.Item;
import jpabook.sbjpashop.repository.MemberRepository;
import jpabook.sbjpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));

        em.persist(member);

        Book book = new Book();
        book.setName("시골 JAP");
        book.setPrice(1000);
        book.setStockQuantity(10);

        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        Order getOrder = orderRepository.findOne(orderId);

        Member member1 = memberRepository.findOne(member.getId());

        assertEquals("상품주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문한 가격은 가격  * 수량이다.", 1000 * 2, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = memberRepository.findOne(1L);

        Order order = orderRepository.findOne(3L);
        System.out.println(22);
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {

    }

}
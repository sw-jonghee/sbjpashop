package jpabook.sbjpashop;

import jpabook.sbjpashop.domain.*;
import jpabook.sbjpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

     private final InitService initService;

     @PostConstruct
     public void init() {
         initService.dbInit1();
         initService.dbInit2();
     }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = getMember("userA", "서울", "1", "123123");

            Book book = createBook("JPA1 Book", 10000, 100);
            em.persist(book);

            Book book2 = createBook("JPA2 Book", 20000, 100);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem1 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem1);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Member getMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            em.persist(member);
            return member;
        }

        public void dbInit2() {
            Member member = getMember("userB", "부산", "2", "1234123");

            Book book = createBook("Spring1 Book", 20000, 200);
            em.persist(book);

            Book book2 = createBook("Spring2 Book", 40000, 300);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 20000, 3);
            OrderItem orderItem1 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem1);
            em.persist(order);
        }
    }
}
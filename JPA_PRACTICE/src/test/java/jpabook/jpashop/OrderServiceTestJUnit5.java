package jpabook.jpashop;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderServiceTestJUnit5 {
   static final int BOOK_STOCK_QUANTITY = 10;
   static final int BOOK_PRICE = 10000;

   @Autowired OrderService orderService;
   @Autowired EntityManager em;

   Member member;
   Item book;

   @BeforeEach
   void setUp() {
       // 회원 등록
       Member member = new Member();
       member.setName("회원");
       member.setAddress(new Address("서울","경기","123-123"));
       em.persist(member);
       this.member = member;

       // 상품(책) 등록
       Book book = new Book();
       book.setName("JPA");
       book.setStockQuantity(BOOK_STOCK_QUANTITY);
       book.setPrice(BOOK_PRICE);
       em.persist(book);
       this.book = book;
   }

   @Test
   void 상품_주문() throws Exception {
       // when
       int orderCount = 2;
       Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

       // then
       Order findOrder = orderService.findOne(orderId);

       assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
       assertThat(findOrder.getOrderItems()).hasSize(1);
       assertThat(book.getStockQuantity()).isEqualTo(BOOK_STOCK_QUANTITY - orderCount);
       assertThat(findOrder.getPriceTotal()).isEqualTo(BOOK_PRICE * orderCount);
   }

   @Test
   void 주문_취소() {
       // given
       int orderCount = 3;
       Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

       // when
       orderService.cancel(orderId);

       // then
       Order findOrder = orderService.findOne(orderId);

       assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
       assertThat(book.getStockQuantity()).isEqualTo(BOOK_STOCK_QUANTITY);
   }

   @Test
   void 재고_수량_초과() {
       Assertions.assertThatThrownBy(() ->
                       orderService.order(member.getId(), book.getId(), BOOK_STOCK_QUANTITY + 1))
                      .isInstanceOf(NotEnoughStockException.class);
    }
}

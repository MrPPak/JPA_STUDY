package jpabook.jpashop;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired OrderService service;
    @Autowired OrderRepository repository;

    @Test
    public void 상품주문() throws Exception {

        // given
        Member member = createMember();
        Item item = createBook("JPA", 10, 10000);

        // when
        int orderCount = 2;
        Long orderId = service.order(member.getId(), item.getId(), orderCount);

        // then
        Order findOrder = repository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, findOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류수가 맞아야한다.", 1, findOrder.getOrderItems().size());
        Assert.assertEquals("주문 수량만큼 재고수가 줄어야 한다.", 8, item.getStockQuantity());
        Assert.assertEquals("주문 가격은 가격 * 수량이다.", orderCount * item.getPrice(), findOrder.getTotalPrice());

    }

    @Test
    public void 주문취소() throws Exception {

        // given
        Member member = createMember();
        Item item = createBook("JPA", 20, 20000);

        int orderCount = 3;
        Long orderId = service.order(member.getId(), item.getId(), orderCount);

        // when
        service.cancelOrder(orderId);

        // then
        Order findOrder = repository.findOne(orderId);

        Assert.assertEquals("주문 취소시 상태는 CANCEL", OrderStatus.CANCEL, findOrder.getStatus());
        Assert.assertEquals("상품의 개수가 원복되어야 한다.", 20, item.getStockQuantity());

    }

    @Test(expected = NotEnoughStockException.class)
    public void 재고수량초과() throws Exception {

        // given
        Member member = createMember();
        Item item = createBook("JPA", 20, 10000);

        int orderCount = 22;

        // when
        service.order(member.getId(), item.getId(), orderCount);

        // then
        Assertions.fail("재고 수량 부족 예외가 발생해야한다.");

    }

    private Item createBook(String name, int stockQuantity, int price) {

        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);

        return book;
    }

    private Member createMember() {

        Member member = new Member();
        member.setName("회원");
        member.setAddress(new Address("서울","경기","123-123"));
        em.persist(member);

        return member;
    }

}

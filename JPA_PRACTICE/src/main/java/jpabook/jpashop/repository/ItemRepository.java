package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            System.out.println("persist전 item의 id = " + item.getId());
            em.persist(item);
            System.out.println("persist후 item의 id = " + item.getId());
        }else {
            em.merge(item);
        }

        System.out.println("영속화 여부: " + em.contains(item));
    }

    public Item findOne(long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void deleteAll() {
        em.createQuery("delete from Item").executeUpdate();
    }

}

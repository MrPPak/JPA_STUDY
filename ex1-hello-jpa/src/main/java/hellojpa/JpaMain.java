package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.internal.build.AllowSysOut;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try { // ctrl + alt + t

            Member member = new Member();
            member.setUsername("member");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            // 히스토리 바꾸기
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        } finally {
            // 라인 이동 : alt + shift + 방향키
            em.close();
        }

        emf.close();
    }
}

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

            Team team = new Team();
            team.setName("A");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("B");
            em.persist(team2);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(20);
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            /*String query = "select m from Member m join fetch m.team where m.team.name = 'A'";
            List<Member> resultList = em.createQuery(query, Member.class).getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1.getUsername() = " + member1.getUsername());
            }*/

            // Member findMember = em.

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

package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try{
            //1. 회원 추가
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            //2. 회원 수정
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            findMember.setName("HelloJPA");
            
            //3. 회원 삭제
//            em.remove(findMember);

            //4. 전체 회원 조회(JPQL) m은 entity
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) //1번부터 가져와
                    .setMaxResults(10) //10개가져옴
                    .getResultList();
            result.stream().forEach(member -> System.out.println("member.name = "+member.getName()) );

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}

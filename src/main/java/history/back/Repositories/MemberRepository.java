package history.back.Repositories;

import history.back.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    public Member findByEmailAndPassword(String email, String password);
}

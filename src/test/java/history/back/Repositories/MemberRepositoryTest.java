package history.back.Repositories;

import history.back.Entities.Member;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findByEmail() {
        Member alex = new Member("alex","alex","alex","alex","alex","alex","alex", Arrays.asList("ROLE_USER"));
        entityManager.persist(alex);
        entityManager.flush();
        Member found = memberRepository.findByEmail(alex.getEmail());
        assertThat(found.getEmail())
                .isEqualTo(alex.getEmail());
    }

}
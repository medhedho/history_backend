package history.back.Business;

import history.back.Entities.Member;
import history.back.Repositories.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
public class MemberBusinessTest {

    @TestConfiguration
    static class MemberBusinessTestContextConfiguration {

        @Bean
        public MemberBusiness memberBusiness() {
            return new MemberBusiness();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return null;
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return false;
                }
            };
        }
    }

    @Autowired
    private MemberBusiness memberBusiness;

    @MockBean
    private MemberRepository memberRepository;

    @Before
    public void setUp() {
        Member alex = new Member("alex","alex","alex","alex","alex","alex","alex", Arrays.asList("ROLE_USER"));

        Mockito.when(memberRepository.findByEmail(alex.getEmail()))
                .thenReturn(alex);
    }

    @Test
    public void getMemberByEmail() {
        String email = "alex";
        Member found = memberBusiness.getMemberByEmail(email);
        assertThat(found.getEmail())
                .isEqualTo(email);
    }
}
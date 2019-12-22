package history.back.Security;

import history.back.Repositories.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MemberDetailsService implements UserDetailsService {

    private MemberRepository users;

    public MemberDetailsService(MemberRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return java.util.Optional.of(this.users.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
    }
}

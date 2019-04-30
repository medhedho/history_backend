package history.back.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy=false)
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long memberid;
    private String name;
    private String family;
    private String country;
    private String ideology;
    private String quote;
    private String email;
    private String password;

    @ElementCollection
    @Builder.Default
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "launcher",cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Discussion> discussions = new ArrayList();

    @OneToMany(mappedBy = "launcher")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Poll> polls = new ArrayList();

    @OneToMany(mappedBy = "contributor",cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Contribution> contributions = new ArrayList();

    @OneToMany(mappedBy = "member")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Vote> votes = new ArrayList();

    public Member(String name, String family, String country, String ideology, String quote, String email, String password) {
        this.name = name;
        this.family = family;
        this.country = country;
        this.ideology = ideology;
        this.quote = quote;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

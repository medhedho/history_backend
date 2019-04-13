package history.back.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy=false)
public class Member {
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
}

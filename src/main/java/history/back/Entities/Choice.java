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

public class Choice {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long choiceid;
    private String text;

    @ManyToOne
    private Poll poll;

    @OneToMany(mappedBy = "choice")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Vote> votes = new ArrayList();
}

package history.back.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy=false)
public class Discussion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long discussionid;
    private String launching;
    private Date date;

    @ManyToOne
    private Member launcher;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "discussion",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Contribution> contributions = new ArrayList();

    public Discussion(String launching, Member launcher) {
        this.launching = launching;
        this.date = new Date();
        this.launcher = launcher;
    }
}

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
public class Poll {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pollid;
    private String launching;
    private Date date;

    @ManyToOne
    private Member launcher;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "poll")
    @JsonIgnore
    private List<Choice> choices = new ArrayList();

    public Poll(String launching, Member launcher) {
        this.launching = launching;
        this.date = new Date();
        this.launcher = launcher;
    }
}

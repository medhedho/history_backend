package history.back.Entities;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy=false)
public class Contribution {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long contributionid;
    private String text;
    private Date date;

    @ManyToOne
    private Member contributor;

    @ManyToOne
    private Discussion discussion;

    public Contribution(String text, Member contributor, Discussion discussion) {
        this.text = text;
        this.date = new Date();
        this.contributor = contributor;
        this.discussion = discussion;
    }
}

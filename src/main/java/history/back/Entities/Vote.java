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
public class Vote {
    @EmbeddedId
    private VoteKey voteid;

    @ManyToOne
    @MapsId("choiceid")
    @JoinColumn(name = "choiceid")
    Choice choice;

    @ManyToOne
    @MapsId("memberid")
    @JoinColumn(name = "memberid")
    Member member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote that = (Vote) o;
        return Objects.equals(member.getMemberid(), that.getMember().getMemberid()) &&
                Objects.equals(choice.getChoiceid(), that.getChoice().getChoiceid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(member.getMemberid(), choice.getChoiceid());
    }
}

package history.back.Entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteKey implements Serializable {
    @Column(name = "memberid")
    Long memberid;

    @Column(name = "choiceid")
    Long choiceid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteKey)) return false;
        VoteKey that = (VoteKey) o;
        return Objects.equals(this.getMemberid(), that.getMemberid()) &&
                Objects.equals(this.getChoiceid(), that.getChoiceid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMemberid(), this.getChoiceid());
    }
}

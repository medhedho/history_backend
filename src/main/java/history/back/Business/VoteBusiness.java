package history.back.Business;

import history.back.Entities.Choice;
import history.back.Entities.Member;
import history.back.Entities.Vote;
import history.back.Entities.VoteKey;
import history.back.Repositories.ChoiceRepository;
import history.back.Repositories.MemberRepository;
import history.back.Repositories.PollRepository;
import history.back.Repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VoteBusiness {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChoiceRepository choiceRepository;

    public Vote createVote(long memberid, long choiceid){
        Vote c = new Vote(new VoteKey(memberid,choiceid),choiceRepository.getOne(choiceid),memberRepository.getOne(memberid));
        voteRepository.save(c);
        return c;
    }

    public Vote getVote(Long id){
        return voteRepository.getOne(id);
    }

    public Vote updateVote(Long id, long choiceid){
        Vote v = voteRepository.getOne(id);
        v.getVoteid().setChoiceid(choiceid);
        v.setChoice(choiceRepository.getOne(choiceid));
        voteRepository.save(v);
        return v;
    }

    public Vote deleteVote(Long id){
        Vote x = voteRepository.getOne(id);
        voteRepository.delete(x);
        return x;
    }

    public List<Vote> getAllVotes(long id){
        List<Vote> all = voteRepository.findAll();
        List<Vote> list = new ArrayList();
        Iterator<Vote> it=all.iterator();
        while(it.hasNext()){
            Vote e=it.next();
            if(e.getChoice().getChoiceid() == id)
                list.add(e);
        }
        return list;
    }
}

package history.back.Business;

import history.back.Entities.Choice;
import history.back.Entities.Vote;
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
public class ChoiceBusiness {
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private VoteRepository voteRepository;

    public Choice createChoice(String text, Long memberid, Long pollid){
        Choice c = new Choice(text,memberRepository.getOne(memberid),pollRepository.getOne(pollid));
        choiceRepository.save(c);
        return c;
    }

    public Choice getChoice(Long id){
        return choiceRepository.getOne(id);
    }

    public Choice updateChoice(Long id, String text){
        Choice c = choiceRepository.getOne(id);
        c.setText(text);
        choiceRepository.save(c);
        return c;
    }

    public Choice deleteChoice(Long id){
        Choice x = choiceRepository.getOne(id);
        choiceRepository.delete(x);
        return x;
    }

    public List<Choice> getAllChoices(long id){
        List<Choice> all = choiceRepository.findAll();
        List<Choice> list = new ArrayList();
        Iterator<Choice> it=all.iterator();
        while(it.hasNext()){
            Choice e=it.next();
            if(e.getPoll().getPollid() == id)
                list.add(e);
        }
        return list;
    }

    public int getVotesNumber(long id){
        Choice c = choiceRepository.getOne(id);
        List<Vote> all = voteRepository.findAll();
        int x = 0;
        Iterator<Vote> it = all.iterator();
        while (it.hasNext()){
            Vote v = it.next();
            if (v.getChoice().getChoiceid()== id)
                x++;
        }
        return x;
    }
}

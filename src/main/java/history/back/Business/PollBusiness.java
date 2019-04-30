package history.back.Business;

import history.back.Business.Keywords.JaccardIndexBasedSimilarity;
import history.back.Entities.Choice;
import history.back.Entities.Discussion;
import history.back.Entities.Poll;
import history.back.Repositories.ChoiceRepository;
import history.back.Repositories.DiscussionRepository;
import history.back.Repositories.PollRepository;
import history.back.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PollBusiness {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private JaccardIndexBasedSimilarity jaccardIndexBasedSimilarity;

    public Poll createPoll(String launching, Long memberid){
        Poll d = new Poll(launching,memberRepository.getOne(memberid));
        pollRepository.save(d);
        /*Iterator<String> iterator = choices.iterator();
        while (iterator.hasNext()){
            String x = iterator.next();
            Choice c = new Choice(x,memberRepository.getOne(memberid),d);
            choiceRepository.save(c);
        }*/
        return d;
    }

    public Poll getPoll(Long id){
        return pollRepository.getOne(id);
    }

    public Poll updatePoll(Long id, String launching){
        Poll d = pollRepository.getOne(id);
        d.setLaunching(launching);
        pollRepository.save(d);
        return d;
    }

    public void deletePoll(Long id){
        pollRepository.delete(pollRepository.getOne(id));
    }

    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    public List<Poll> getRelatedPolls(Long id){
        Poll thepoll = pollRepository.getOne(id);
        List<Poll> polls = pollRepository.findAll();
        List<Poll> related = new ArrayList();
        Iterator<Poll> iterator = polls.iterator();
        while (iterator.hasNext()){
            Poll p = iterator.next();
            if (jaccardIndexBasedSimilarity.calculateSimilarity(thepoll.getLaunching(),p.getLaunching())>=0.05){
                related.add(p);
            }
        }
        return related;
    }

    public List<Discussion> getRelatedDiscussions(Long id){
        Poll thepoll = pollRepository.getOne(id);
        List<Discussion> discussions = discussionRepository.findAll();
        List<Discussion> related = new ArrayList();
        Iterator<Discussion> iterator = discussions.iterator();
        while (iterator.hasNext()){
            Discussion p = iterator.next();
            if (jaccardIndexBasedSimilarity.calculateSimilarity(thepoll.getLaunching(),p.getLaunching())>=0.05){
                related.add(p);
            }
        }
        return related;
    }
    
    

}

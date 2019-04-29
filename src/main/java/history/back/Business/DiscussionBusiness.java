package history.back.Business;

import history.back.Business.Keywords.JaccardIndexBasedSimilarity;
import history.back.Entities.Discussion;
import history.back.Entities.Poll;
import history.back.Repositories.DiscussionRepository;
import history.back.Repositories.MemberRepository;
import history.back.Repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DiscussionBusiness {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    JaccardIndexBasedSimilarity jaccardIndexBasedSimilarity;
    @Autowired
    PollRepository pollRepository;

    public Discussion createDiscussion(String launching,Long memberid){
        Discussion d = new Discussion(launching,memberRepository.getOne(memberid));
        discussionRepository.save(d);
        return d;
    }

    public Discussion getDiscussion(Long id){
        return discussionRepository.getOne(id);
    }

    public Discussion updateDiscussion(Long id, String launching){
        Discussion d = discussionRepository.getOne(id);
        d.setLaunching(launching);
        discussionRepository.save(d);
        return d;
    }

    public void deleteDiscussion(Long id){
        discussionRepository.delete(discussionRepository.getOne(id));
    }

    public List<Discussion> getAllDiscussions(){
        return discussionRepository.findAll();
    }

    public List<Poll> getRelatedPolls(Long id){
        Discussion thediscussion = discussionRepository.getOne(id);
        List<Poll> polls = pollRepository.findAll();
        List<Poll> related = new ArrayList();
        Iterator<Poll> iterator = polls.iterator();
        while (iterator.hasNext()){
            Poll p = iterator.next();
            if (jaccardIndexBasedSimilarity.calculateSimilarity(thediscussion.getLaunching(),p.getLaunching())>0.05){
                related.add(p);
            }
        }
        return related;
    }

    public List<Discussion> getRelatedDiscussions(Long id){
        Discussion thediscussion = discussionRepository.getOne(id);
        List<Discussion> discussions = discussionRepository.findAll();
        List<Discussion> related = new ArrayList();
        Iterator<Discussion> iterator = discussions.iterator();
        while (iterator.hasNext()){
            Discussion p = iterator.next();
            if (jaccardIndexBasedSimilarity.calculateSimilarity(thediscussion.getLaunching(),p.getLaunching())>0.05){
                related.add(p);
            }
        }
        return related;
    }
}

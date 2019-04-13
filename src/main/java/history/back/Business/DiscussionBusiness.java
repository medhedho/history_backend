package history.back.Business;

import history.back.Entities.Discussion;
import history.back.Repositories.DiscussionRepository;
import history.back.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionBusiness {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private MemberRepository memberRepository;

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

}

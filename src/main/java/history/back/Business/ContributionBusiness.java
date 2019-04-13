package history.back.Business;

import history.back.Entities.Contribution;
import history.back.Repositories.ContributionRepository;
import history.back.Repositories.DiscussionRepository;
import history.back.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ContributionBusiness {
    @Autowired
    private ContributionRepository contributionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscussionRepository discussionRepository;

    public Contribution createContribution(String text, Long memberid, Long discussionid){
        Contribution c = new Contribution(text,memberRepository.getOne(memberid),discussionRepository.getOne(discussionid));
        contributionRepository.save(c);
        return c;
    }

    public Contribution getContribution(Long id){
        return contributionRepository.getOne(id);
    }

    public Contribution updateContribution(Long id, String text){
        Contribution c = contributionRepository.getOne(id);
        c.setText(text);
        contributionRepository.save(c);
        return c;
    }

    public Contribution deleteContribution(Long id){
        Contribution x = contributionRepository.getOne(id);
        contributionRepository.delete(x);
        return x;
    }

    public List<Contribution> getAllContributions(long id){
        List<Contribution> all = contributionRepository.findAll();
        List<Contribution> list = new ArrayList();
        Iterator<Contribution> it=all.iterator();
        while(it.hasNext()){
            Contribution e=it.next();
            if(e.getDiscussion().getDiscussionid() == id)
                list.add(e);
        }
        return list;
    }
}

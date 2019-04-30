package history.back.Controllers;

import history.back.Business.DiscussionBusiness;
import history.back.Entities.Discussion;
import history.back.Entities.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DiscussionController {
    @Autowired
    private DiscussionBusiness discussionBusiness;

    @PostMapping("/discussion")
    public Discussion createDiscussion(@RequestParam("launcher") long memberid, @RequestBody Discussion d){
        return discussionBusiness.createDiscussion(d.getLaunching(),memberid);
    }

    @GetMapping(value = "/discussion/{id}")
    public Discussion getDiscussion(@PathVariable("id") long id){
        return discussionBusiness.getDiscussion(id);
    }

    @PutMapping(value = "/discussion/{id}")
    public Discussion updateDiscussion(@PathVariable("id") long id, @RequestBody Discussion d){
        return discussionBusiness.updateDiscussion(id,d.getLaunching());
    }

    @CrossOrigin(origins="*",methods = {RequestMethod.DELETE})
    @DeleteMapping(value = "/discussion/{id}")
    public String  deleteDiscussion(@PathVariable("id") long id){
        discussionBusiness.deleteDiscussion(id);
        return "{\"msg\":\"sucessfully deleted\"}";
    }

    @GetMapping("/discussions")
    public List<Discussion> getAllDiscussions(){
        return discussionBusiness.getAllDiscussions();
    }

    @GetMapping("/discussionreld/{id}")
    public List<Discussion> getRelatedDiscussions(@PathVariable long id){
        return discussionBusiness.getRelatedDiscussions(id);
    }
    @GetMapping("/discussionrelp/{id}")
    public List<Poll> getRelatedPolls(@PathVariable long id){
        return discussionBusiness.getRelatedPolls(id);
    }

}

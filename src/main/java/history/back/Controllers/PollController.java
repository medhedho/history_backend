package history.back.Controllers;

import history.back.Business.PollBusiness;
import history.back.Entities.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PollController {
    @Autowired
    private PollBusiness pollBusiness;

    @PostMapping("/poll")
    public Poll createPoll(@RequestParam("launcher") long memberid, @RequestBody Poll d){
        return pollBusiness.createPoll(d.getLaunching(),memberid);
    }

    @GetMapping(value = "/poll/{id}")
    public Poll getPoll(@PathVariable("id") long id){
        return pollBusiness.getPoll(id);
    }

    @PutMapping(value = "/poll/{id}")
    public Poll updatePoll(@PathVariable("id") long id, @RequestBody Poll d){
        return pollBusiness.updatePoll(id,d.getLaunching());
    }

    @CrossOrigin(origins="*",methods = {RequestMethod.DELETE})
    @DeleteMapping(value = "/poll/{id}")
    public String  deletePoll(@PathVariable("id") long id){
        pollBusiness.deletePoll(id);
        return "{\"msg\":\"sucessfully deleted\"}";
    }

    @GetMapping("/polls")
    public List<Poll> getAllPolls(){
        return pollBusiness.getAllPolls();
    }
}

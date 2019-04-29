package history.back.Controllers;

import history.back.Business.VoteBusiness;
import history.back.Entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class VoteController {
    @Autowired
    private VoteBusiness voteBusiness;

    @PostMapping("/vote")
    public Vote createVote(@RequestParam("voter") long memberid, @RequestParam("choice") long choiceid){
        return voteBusiness.createVote(memberid,choiceid);
    }

    @GetMapping(value = "/vote/{id}")
    public Vote getVote(@PathVariable("id") long id){
        return voteBusiness.getVote(id);
    }

    @PutMapping(value = "/vote/{id}")
    public Vote updateVote(@PathVariable("id") long id, @RequestParam("choice") long choiceid){
        return voteBusiness.updateVote(id,choiceid);
    }

    @CrossOrigin(origins="*",methods = {RequestMethod.DELETE})
    @DeleteMapping(value = "/vote/{id}")
    public String  deleteVote(@PathVariable("id") long id){
        voteBusiness.deleteVote(id);
        return "{\"msg\":\"sucessfully deleted\"}";
    }

    @GetMapping("/votes")
    public List<Vote> getAllVotes(@RequestParam("choice") long choiceid){
        return voteBusiness.getAllVotes(choiceid);
    }
}

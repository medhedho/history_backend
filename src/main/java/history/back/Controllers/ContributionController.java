package history.back.Controllers;

import history.back.Business.ContributionBusiness;
import history.back.Entities.Contribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ContributionController {
    @Autowired
    private ContributionBusiness contributionBusiness;

    @PostMapping("/contribution")
    public Contribution createContribution(@RequestParam("contributor") long memberid, @RequestParam("discussion") long discussionid, @RequestBody Contribution c){
        return contributionBusiness.createContribution(c.getText(),memberid,discussionid);
    }

    @GetMapping(value = "/contribution/{id}")
    public Contribution getContribution(@PathVariable("id") long id){
        return contributionBusiness.getContribution(id);
    }

    @PutMapping(value = "/contribution/{id}")
    public Contribution updateContribution(@PathVariable("id") long id, @RequestBody Contribution c){
        return contributionBusiness.updateContribution(id,c.getText());
    }

    @DeleteMapping(value = "/contribution/{id}")
    public Contribution deleteContribution(@PathVariable("id") long id){
        return contributionBusiness.deleteContribution(id);
    }

    @GetMapping(value = "/contributions/{id}")
    public List<Contribution> getContributions(@PathVariable("id") long id) { return contributionBusiness.getAllContributions(id); }
}
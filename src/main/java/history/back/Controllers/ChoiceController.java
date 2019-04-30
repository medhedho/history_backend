package history.back.Controllers;

import history.back.Business.ChoiceBusiness;
import history.back.Entities.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ChoiceController {
    @Autowired
    private ChoiceBusiness choiceBusiness;

    @PostMapping("/choice")
    public Choice createChoice(@RequestParam("creator") long memberid, @RequestParam("poll") long pollid, @RequestBody Choice c){
        return choiceBusiness.createChoice(c.getText(),memberid,pollid);
    }

    @GetMapping("/choicevotes/{id}")
    public int getVotesNumber(@PathVariable long id){
        return choiceBusiness.getVotesNumber(id);
    }

    @GetMapping(value = "/choice/{id}")
    public Choice getChoice(@PathVariable("id") long id){
        return choiceBusiness.getChoice(id);
    }

    @PutMapping(value = "/choice/{id}")
    public Choice updateChoice(@PathVariable("id") long id, @RequestBody Choice c){
        return choiceBusiness.updateChoice(id,c.getText());
    }

    @DeleteMapping(value = "/choice/{id}")
    public Choice deleteChoice(@PathVariable("id") long id){
        return choiceBusiness.deleteChoice(id);
    }

    @GetMapping(value = "/choices/{id}")
    public List<Choice> getChoices(@PathVariable("id") long id) { return choiceBusiness.getAllChoices(id); }
}
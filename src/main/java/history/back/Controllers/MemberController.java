package history.back.Controllers;

import history.back.Business.MemberBusiness;
import history.back.Entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {
    @Autowired
    private MemberBusiness memberBusiness;

    @PostMapping("/member")
    public Member createMember(@RequestBody Member m){
        return memberBusiness.createMember(m.getName(),m.getFamily(),m.getCountry(),m.getIdeology(),m.getQuote(),m.getEmail(),m.getPassword(),m.getRoles());
    }

    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable long id){
        return memberBusiness.getMember(id);
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable long id, @RequestBody Member member){
        return memberBusiness.updateMember(memberBusiness.getMember(id),member.getName(),member.getFamily(),member.getCountry(),member.getIdeology(),member.getQuote(),member.getEmail(),member.getPassword());
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable long id){
        memberBusiness.deleteMember(id);
    }

    @GetMapping("/members")
    public List<Member> getAllMembers(){
        return memberBusiness.getAllMembers();
    }

}
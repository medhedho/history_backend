package history.back.Business;

import history.back.Entities.Member;
import history.back.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberBusiness {
    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(String name, String family, String country, String ideology, String quote, String email, String password){
        Member m = new Member(name,family,country,ideology,quote,email,password);
        memberRepository.save(m);
        return m;
    }

    public Member getMember(Long id){
        return memberRepository.getOne(id);
    }

    public Member updateMember(Member m,String name,String family,String country,String ideology,String quote,String email,String password){
        m.setName(name);m.setFamily(family);m.setCountry(country);m.setIdeology(ideology);m.setQuote(quote);m.setEmail(email);m.setPassword(password);
        memberRepository.save(m);
        return m;
    }

    public void deleteMember(Long id){
        memberRepository.delete(memberRepository.getOne(id));
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member signUp(){
        return new Member();
    }

    public Member signIn(String email, String password){
        return memberRepository.findByEmailAndPassword(email,password);
    }

}

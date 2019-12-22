package history.back.Business;

import history.back.Entities.Member;
import history.back.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MemberBusiness {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String name, String family, String country, String ideology, String quote, String email, String password, List<String> roles){
        Member m = new Member(name,family,country,ideology,quote,email,passwordEncoder.encode(password),roles);
        memberRepository.save(m);
        return m;
    }

    public Member getMember(Long id){
        return memberRepository.getOne(id);
    }

    public Member getMemberByEmail(String email) {
        return new Member();
        //return memberRepository.findByEmail(email);
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
}

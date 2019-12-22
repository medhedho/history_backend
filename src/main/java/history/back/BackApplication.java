package history.back;

import history.back.Business.DiscussionBusiness;
import history.back.Business.Keywords.JaccardIndexBasedSimilarity;
import history.back.Business.MemberBusiness;
import history.back.Entities.Discussion;
import history.back.Entities.Member;
import history.back.Entities.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) { final ApplicationContext ctx = SpringApplication.run(BackApplication.class, args); }

    /*@Bean
    public CommandLineRunner demo() {
        try {
            return (args) -> {
                //System.out.println(memberBusiness.signIn("icloud","icloud").getName());
                Discussion discussion = discussionBusiness.getDiscussion(23L);
                List<Discussion> reld = discussionBusiness.getRelatedDiscussions(23L);
                Iterator<Discussion> it = reld.iterator();
                while (it.hasNext())
                {
                    Discussion d = it.next();
                    System.out.println(d.getLaunching());
                }
                System.out.println("****");
                List<Poll> relp = discussionBusiness.getRelatedPolls(23L);
                Iterator<Poll> itt = relp.iterator();
                while (itt.hasNext())
                {
                    Poll p = itt.next();
                    System.out.println(p.getLaunching());
                }
                System.out.println(jaccardIndexBasedSimilarity.calculateSimilarity("Does Crimea really belong to Russia?","Was the Bolshevik revolution a conspiracy against Christianity in Russia?"));
                System.out.println(jaccardIndexBasedSimilarity.calculateSimilarity("Does Crimea really belong to Russia?","Which country should Kashmir belong to?"));
                System.out.println(jaccardIndexBasedSimilarity.calculateSimilarity("Does Crimea really belong to Russia?","Which country would be best suited to the Tatars of Crimea?"));
                System.out.println(jaccardIndexBasedSimilarity.calculateSimilarity("Does Crimea really belong to Russia?","Was the Bolshevik revolution a conspiracy against Christianity in Russia?"));
            };
        }
        catch (Exception ex){}
        return null;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4444");
            }
        };
    }*/

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

    @Bean
    public AuditorAware<Member> auditor() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Member.class::cast);
    }
}



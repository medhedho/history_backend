package history.back;

import history.back.Business.DiscussionBusiness;
import history.back.Business.Keywords.JaccardIndexBasedSimilarity;
import history.back.Business.MemberBusiness;
import history.back.Entities.Discussion;
import history.back.Entities.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class BackApplication {

    @Autowired
    private MemberBusiness memberBusiness;
    @Autowired
    private DiscussionBusiness discussionBusiness;
    @Autowired
    JaccardIndexBasedSimilarity jaccardIndexBasedSimilarity;

    public static void main(String[] args) { SpringApplication.run(BackApplication.class, args); }

    @Bean
    public CommandLineRunner demo() {
        try {
            return (args) -> {
                System.out.println(memberBusiness.signIn("icloud","icloud").getName());
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
                System.out.println(jaccardIndexBasedSimilarity.calculateSimilarity("Does Crimea really belong to Russia?","Which country would be best suited to the Crimean Tatars?"));

            };
        }
        catch (Exception ex){}

        return null;
    }

}

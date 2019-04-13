package history.back;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) { SpringApplication.run(BackApplication.class, args); }

    @Bean
    public CommandLineRunner demo() {
        try {
            return (args) -> {
            };
        }
        catch (Exception ex){}

        return null;
    }

}

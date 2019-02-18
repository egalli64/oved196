package dd;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        SpringApplication.run(HrApplication.class, args);
    }
}

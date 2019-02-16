package dd;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OvedApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        SpringApplication.run(OvedApplication.class, args);
    }
}

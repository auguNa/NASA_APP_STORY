package NASA_APP_STORY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NASAAppStoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NASAAppStoryApplication.class, args);
    }
}

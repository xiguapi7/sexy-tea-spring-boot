package cloud.xiguapi.starbuck;

import cloud.xiguapi.starbuck.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarbuckNewsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StarbuckNewsApplication.class, args);
    }

    @Autowired
    private SpiderService service;

    @Override
    public void run(String... args) throws Exception {
        service.getData();
    }
}

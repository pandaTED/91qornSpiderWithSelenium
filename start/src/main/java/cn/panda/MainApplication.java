package cn.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\panda\\chromedriver_win32 (6)\\chromedriver.exe");
        SpringApplication.run(MainApplication.class, args);
    }

}

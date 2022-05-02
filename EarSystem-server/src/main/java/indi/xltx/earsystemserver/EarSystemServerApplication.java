package indi.xltx.earsystemserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan
@EnableScheduling
public class EarSystemServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EarSystemServerApplication.class, args);
    }

}
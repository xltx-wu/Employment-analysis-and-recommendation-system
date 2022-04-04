package indi.xltx.earsystemserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class EarSystemServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EarSystemServerApplication.class, args);
    }

}
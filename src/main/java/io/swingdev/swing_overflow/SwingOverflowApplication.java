package io.swingdev.swing_overflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "me.ramswaroop.jbot",
        "io.swingdev.swing_overflow"
})
public class SwingOverflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwingOverflowApplication.class, args);
    }
}

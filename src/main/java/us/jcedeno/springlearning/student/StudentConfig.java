package us.jcedeno.springlearning.student;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            repository.save(Student.of("Juan", "jcedeno@noobsters.net", LocalDate.of(2001, Month.SEPTEMBER, 7)));
        };
    }

}

package io.snowdrop.narayana;

import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.ats.jbossatx.jta.RecoveryManagerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jta.narayana.NarayanaRecoveryManagerBean;
import org.springframework.context.annotation.Bean;

/**
 * Main Spring Boot application class.
 *
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@SpringBootApplication
public class ExampleApplication {

    public static void main(String... args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    /**
     * Override NarayanaRecoveryManagerBean. Currently NarayanaRecoveryManagerBean provided by Spring Boot cannot be
     * replaces, so a change in Spring Boot is needed.
     *
     * @param recoveryManagerService Recovery manager service which should be started.
     * @return
     */
    @Bean
    public NarayanaRecoveryManagerBean narayanaRecoveryManager(RecoveryManagerService recoveryManagerService) {
        RecoveryManager.delayRecoveryManagerThread();
        return new CustomNarayanaRecoveryManagerBean(recoveryManagerService);
    }

}

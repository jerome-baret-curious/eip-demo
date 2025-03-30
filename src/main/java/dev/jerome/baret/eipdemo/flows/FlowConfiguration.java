package dev.jerome.baret.eipdemo.flows;

import dev.jerome.baret.eipdemo.entities.InputData;
import dev.jerome.baret.eipdemo.entities.ResultData;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;

import java.time.Duration;

@Configuration
@EnableIntegration
public class FlowConfiguration {

    private final EntityManagerFactory entityManagerFactory;

    public FlowConfiguration(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public IntegrationFlow demoFlow() {
        return IntegrationFlow.from(Jpa.inboundAdapter(this.entityManagerFactory)
                                .entityClass(InputData.class)
                                .maxResults(1)
                                .expectSingleResult(true),
                        e -> e.poller(Pollers.fixedRate(Duration.ofSeconds(10))))
                .filter((InputData p) -> Boolean.FALSE.equals(p.getDone()))
                .<InputData>log(m -> "Processing input id: " + m.getPayload().getId())
                .transform((InputData p) -> {
                    ResultData resultData = new ResultData();

                    StringBuilder sb = new StringBuilder();
                    if (p.getFirstName() != null && !p.getFirstName().isEmpty()) {
                        sb.append(p.getFirstName().charAt(0));
                    }
                    if (p.getLastName() != null && !p.getLastName().isEmpty()) {
                        sb.append(p.getLastName().charAt(0));
                    }
                    resultData.setInitials(sb.toString());
                    resultData.setInput(p);

                    return resultData;
                })
                .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                                .entityClass(ResultData.class)
                                .persistMode(PersistMode.PERSIST),
                        e -> e.transactional())
                .get();
    }
}

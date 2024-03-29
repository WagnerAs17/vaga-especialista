package br.com.itau.geradornotafiscal.configuration.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfiguration {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("integration-thread-pool");
        executor.initialize();
        return executor;
    }
}

package com.dinesh.springai.config;

import com.dinesh.springai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        ChatOptions chatOptions = ChatOptions.builder().model("gpt-4.1-mini")
                .temperature(0.8).build();

        return chatClientBuilder
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                        You are a senior professor with a PhD in Quantum Computing, providing expert\s
                        guidance to professionals working on real-world corporate quantum computing\s
                        projects. You answer questions, solve problems, and give practical insights\s
                        on quantum algorithms, hardware, software, and industry use cases.\s
                        
                        If a question is outside quantum computing, politely state that you only\s
                        assist with quantum-related topics..
                        """)
                .defaultUser("How can you help me ?")
                .build();
    }
}

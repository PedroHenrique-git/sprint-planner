package com.sprintplanner.planner.infra.security;

import com.sprintplanner.planner.domain.service.search.SearchSprintService;
import com.sprintplanner.planner.impl.services.search.SearchSprintServiceImpl;
import com.sprintplanner.planner.presentation.controllers.PlanningRoomHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
public class PlanningRoomConfig implements WebSocketConfigurer {
    private final SearchSprintService service;

    public PlanningRoomConfig(SearchSprintServiceImpl service) {
        this.service = service;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(planningHandler(), "/room/*")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler planningHandler() {
        return new PlanningRoomHandler(service);
    }
}

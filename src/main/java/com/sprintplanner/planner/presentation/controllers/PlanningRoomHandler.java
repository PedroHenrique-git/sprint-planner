package com.sprintplanner.planner.presentation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintplanner.planner.domain.model.search.SearchSprint;
import com.sprintplanner.planner.domain.service.search.SearchSprintService;
import com.sprintplanner.planner.impl.services.search.SearchSprintServiceImpl;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class PlanningRoomHandler implements WebSocketHandler {
    private final SearchSprintService service;

    public PlanningRoomHandler(SearchSprintService service) {
        this.service = service;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        session.sendMessage(
                new TextMessage(
                        buildJsonResponse(Map.of("message", "leaving planning session", "status", "ok"))
                )
        );
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sprintId = getSprintIdFromUri(Objects.requireNonNull(session.getUri()).getPath());

        var sprint = service.get(sprintId);

        if(sprint.isEmpty()) {
            session.sendMessage(
                    new TextMessage(
                            buildJsonResponse(Map.of("message", "sprint not found", "status", "not_found"))
                    )
            );

            return;
        }

        session.sendMessage(
                new TextMessage(
                    buildJsonResponse(
                            Map.of("message", String.format("Entering planning session for sprint 123 %s", sprint.get().getName()), "status", "ok")
                    )
                )
        );
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        session.sendMessage(new TextMessage("message"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Exception occurred: {} on session: {}", exception.getMessage(), session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getSprintIdFromUri(String uri) {
        return Arrays.asList(uri.split("/")).getLast();
    }

    private String buildJsonResponse(Map<String, String> data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}

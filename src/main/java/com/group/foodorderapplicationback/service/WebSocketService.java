package com.group.foodorderapplicationback.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void notifyFrontend(String messageContent) {
        Message message = new Message(messageContent);
        simpMessagingTemplate.convertAndSend("/topic/messages", message);
    }

    public void notifyStaff(String messageContent) {
        Message message = new Message(messageContent);
        simpMessagingTemplate.convertAndSend("/topic/staff", message);
    }

    public void notifyAllDeliveryUsers(String messageContent) {
        Message message = new Message(messageContent);
        simpMessagingTemplate.convertAndSend("/topic/delivery-user", message);
    }

    public void notifyDeliveryUser(String messageContent, String username) {
        Message message = new Message(messageContent);
        simpMessagingTemplate.convertAndSend("/topic/delivery-user/" + username, message);
    }

    public void notifyCustomer(String messageContent, String username) {
        Message message = new Message(messageContent);
        log.info("username to notify: " + username);
        simpMessagingTemplate.convertAndSend("/topic/customer/" + username, message);
    }

    @Data
    @AllArgsConstructor
    public class Message {
        private String messageContent;
    }

}
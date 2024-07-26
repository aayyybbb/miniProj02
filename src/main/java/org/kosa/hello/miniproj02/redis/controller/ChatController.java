package org.kosa.hello.miniproj02.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private final RedisTemplate<String, String> redisTemplate;
    private final ChannelTopic topic;

    @GetMapping("/chat")
    public String chatPage(Model model) {
        return "/chat/chatPage";
    }

    public ChatController(RedisTemplate<String, String> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}


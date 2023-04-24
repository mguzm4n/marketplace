package com.example.authjava.Controllers;

import com.example.authjava.Data.DTOs.MessageProjection;
import com.example.authjava.Data.Models.CustomUserDetails;
import com.example.authjava.Data.Models.MessageModel;
import com.example.authjava.Data.Models.UserModel;
import com.example.authjava.Services.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<MessageModel> create(@PathVariable Long productId,
                                               @RequestBody MessageRequest request,
                                               @AuthenticationPrincipal CustomUserDetails authDetails) {
        UserModel user = authDetails.getUser();
        log.info("> String is {}", request.getContent());
        return ResponseEntity.ok(
            messageService.create(productId, user.getId(), request.getContent())
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Map<Long, List<MessageProjection>>> findAll(@PathVariable Long productId) {
        return ResponseEntity.ok(
            messageService.findAllByProductId(productId)
        );
    }

    @GetMapping("/product/{productId}/user/{userId}")
    public void getSentToProductByUser() {

    }

    @GetMapping("/user/{userId}")
    public void getAllSentByUser() {

    }

    @Data
    public static class MessageRequest {
        private String content;
    }
}

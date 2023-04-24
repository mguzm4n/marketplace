package com.example.authjava.Controllers;

import com.example.authjava.Data.Models.CustomUserDetails;
import com.example.authjava.Services.ReactionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reactions")
@AllArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;
    @PutMapping("/message/{messageId}")
    public void updateReaction(@PathVariable Long messageId, @RequestBody ReactionRequest req, @AuthenticationPrincipal CustomUserDetails auth) {
        var user = auth.getUser();
        reactionService.updateReaction(messageId, req.getNewReactionId(), user.getId());
    }

    @DeleteMapping("/message/{messageId}")
    public void deleteReaction(@PathVariable Long messageId, @AuthenticationPrincipal CustomUserDetails auth) {
        var user = auth.getUser();
        reactionService.deleteReaction(messageId, user.getId());
    }

    @Data
    public static class ReactionRequest {
        private Long newReactionId;
    }
}

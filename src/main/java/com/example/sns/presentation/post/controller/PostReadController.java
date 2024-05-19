package com.example.sns.presentation.post.controller;

import com.example.sns.application.dto.post.GetPostsByCursorCommand;
import com.example.sns.application.dto.post.GetPostsResponse;
import com.example.sns.application.dto.post.GetTimelineByCursorCommand;
import com.example.sns.application.usercaseImpl.post.GetPostUsecase;
import com.example.sns.application.usercaseImpl.post.GetPostsByCursorUsecase;
import com.example.sns.application.usercaseImpl.post.GetTimelinePostUsecase;
import com.example.sns.presentation.post.controller.request.GetPostsByCursorRequest;
import com.example.sns.presentation.post.controller.response.PostResponse;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostReadController {

    private final GetPostUsecase getPostUsecase;
    private final GetPostsByCursorUsecase getPostsByCursorUsecase;
    private final GetTimelinePostUsecase getTimelinePostUsecase;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        ResponseEntity<PostResponse> response = getPostUsecase.getById(id);
        return ResponseEntity.ok(response.getBody());
    }


    @GetMapping("/users/{userId}/by-cursor")
    public ResponseEntity<CursorResponse<GetPostsResponse>> getPosts(
            @PathVariable("userId") Long writerId,
            @Valid @RequestBody GetPostsByCursorRequest request) {

        var command = GetPostsByCursorCommand.of(writerId, new CursorRequest(request.getSize(), request.getKey()));

        CursorResponse<GetPostsResponse> posts = getPostsByCursorUsecase.execute(command);
        return ResponseEntity.ok(posts);
    }

    //TODO : 생성 시에 큰 부하, 캐시 쓰거나, 나중에 NoSQL DB로 바꾸기.
//    @GetMapping("/users/{userId}/timeline")
//    public ResponseEntity<List<PostDto>> getTimeline(@PathVariable("userId") Long userId, @RequestBody GetTimelineByCursorRequest request) {
//        var command = GetTimelineByCursorCommand.of(userId, new CursorRequest(request.getSize(), request.getKey()));
//        List<PostDto> execute = getTimelinePostUsecase.execute(command);
//        return ResponseEntity.ok(execute);
//    }

    //    @GetMapping("/users/{userId}/timeline")
//    public ResponseEntity<List<PostDto>> getTimeline(
//            @PathVariable("userId") Long userId,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) Long key) {
//        var command = GetTimelineByCursorCommand.of(userId, new CursorRequest(size, key));
//        List<PostDto> execute = getTimelinePostUsecase.execute(command);
//        return ResponseEntity.ok(execute);
//    }
    @GetMapping("/users/{userId}/timeline")
    public ResponseEntity<CursorResponse> getTimeline(
            @PathVariable("userId") Long userId,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long key) {
        var command = GetTimelineByCursorCommand.of(userId, new CursorRequest(size, key));
        CursorResponse execute = getTimelinePostUsecase.execute(command);
        return ResponseEntity.ok(execute);
    }
}

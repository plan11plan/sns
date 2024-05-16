package com.example.sns.core.post.controller;


import com.example.sns.core.post.controller.request.PostUpdateRequest;
import com.example.sns.core.post.controller.response.PostResponse;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.service.PostReadService;
import com.example.sns.core.post.service.PostUpdateService;
import com.example.sns.core.post.service.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostUpdateService postUpdateService;
    private final PostReadService postReadService;
//    private final GetTimelinePostUsecase getTimelinePostUsecase;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable long id){
        return ResponseEntity.ok(PostResponse.from(PostDto.from(postReadService.getById(id)),null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable long id, @RequestBody PostUpdateRequest request){
        return ResponseEntity.ok(PostResponse.from(PostDto.from(postUpdateService.update(id,request.toDomainRequest())),null));
    }

    @GetMapping("/users/{userId}/by-cursor")
    public CursorResponse<Post> getPosts(
            @PathVariable("userId") Long writerId,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long key){
        CursorRequest cursorRequest = new CursorRequest(size, key);
        PostStatus published = PostStatus.PUBLISHED;
        return postReadService.getPosts(writerId,published,cursorRequest);
    }
    //TODO : 생성 시에 큰 부하, 캐시 쓰거나, 나중에 NoSQL DB로 바꾸기.
//    @GetMapping("/usrId/{userId}/timeline")
//    public CursorResponse<Post> getTimeline(@PathVariable Long userId, CursorRequest cursorRequest
//    ){
//        return getTimelinePostUsecase.execute(userId,cursorRequest);
//    }
}

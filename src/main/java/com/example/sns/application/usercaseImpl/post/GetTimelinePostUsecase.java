//package com.example.sns.application.usercaseImpl.post;
//
//import static java.util.stream.Collectors.toList;
//
//import com.example.sns.core.follow.service.read.FollowReadService;
//import com.example.sns.core.follow.service.request.FollowGetDto;
//import com.example.sns.core.follow.service.response.FollowDto;
//import com.example.sns.core.post.domain.entity.CursorRequest;
//import com.example.sns.core.post.domain.entity.Post;
//import com.example.sns.core.post.domain.entity.Timeline;
//import com.example.sns.core.post.service.PostReadService;
//import com.example.sns.core.post.service.TimelineReadService;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class GetTimelinePostUsecase {
//    private final PostReadService postReadService;
//    private final FollowReadService followReadService;
//    private final TimelineReadService timelineReadService;
//
//    //TODO 1. Timeline 조회, timeline에 해당하는 게시물을 조회한다.
//    PageCursor<Post> execute(Long userId, CursorRequest cursorRequest){
//        List<Timeline> pagedTimelines = timelineReadService.getTimelines(userId, cursorRequest);;
//        List<Long> postIds = pagedTimelines.stream()
//                .map(Timeline::getPostId).toList();
//        List<Post> posts = postReadService.getPosts(postIds);
//        return  new PageCursor(pagedTimelines.nextCursorRequest(),posts);
//    }
//}

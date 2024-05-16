//package com.example.sns.core.post.service;
//
//import com.example.sns.core.post.domain.entity.CursorRequest;
//import com.example.sns.core.post.domain.entity.CursorResponse;
//import com.example.sns.core.post.domain.entity.Timeline;
//import com.example.sns.core.post.service.port.TimelineRepository;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class TimelineReadService {
//    private final TimelineRepository timelineRepository;
//
//    public List<Timeline> getTimelines(Long userId,CursorRequest cursorRequest){
//        List<Timeline> timelines = findAllBy(userId, cursorRequest);
//        Long nextKey = timelines.stream().mapToLong(Timeline::getId)
//                .min().orElse(CursorRequest.NONE_KEY);
//        return new CursorResponse(cursorRequest.next(nextKey), timelines);
//    }
//    public List<Timeline> findAllBy(Long userId, CursorRequest cursorRequest){
//       if(cursorRequest.hasKey()){
//           return timelineRepository.findAllByLessThanUserIdAndOrderByIdDesc(cursorRequest.key(), userId, cursorRequest.size());
//       }
//       return timelineRepository.findAllByUSerIdAndOrderByIdDesc(userId,cursorRequest.size());
//
//    }
//    private static Long getNextKey(List<Timeline> timelines){
//        return timelines.stream()
//                .mapToLong(Timeline::getId)
//                .min()
//                .orElse(CursorRequest.NONE_KEY);
//    }
//}

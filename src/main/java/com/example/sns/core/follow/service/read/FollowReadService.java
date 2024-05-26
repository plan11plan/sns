package com.example.sns.core.follow.service.read;


import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.exception.FollowerNotFoundException;
import com.example.sns.core.follow.exception.FollowingNotFoundException;
import com.example.sns.core.follow.service.input.FollowGetInput;
import com.example.sns.core.follow.service.output.FollowOutput;
import com.example.sns.core.follow.service.port.FollowRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowReadService {

    private final FollowRepository followRepository;

    public List<FollowOutput> getFollowings(FollowGetInput request) {
        List<Follow> follows = followRepository.findAllByFollowerIdAndStatus(request.getUserId(),request.getStatus())
                .orElseThrow(FollowingNotFoundException::new);
        return FollowOutput.from(follows);
    }

    public List<FollowOutput> getFollowers(FollowGetInput request){
        List<Follow> follows = followRepository.findAllByFollowingIdAndStatus(request.getUserId(),request.getStatus())
                .orElseThrow(FollowerNotFoundException::new);
        return FollowOutput.from(follows);
    }
}

package com.example.sns.core.follow.domain;

import com.example.sns.core.follow.domain.request.FollowAcceptionDetails;
import com.example.sns.core.follow.domain.request.FollowCancelDetails;
import com.example.sns.core.follow.domain.request.FollowCreationDetails;
import com.example.sns.core.follow.domain.request.FollowDeleteDetails;
import com.example.sns.core.follow.domain.request.FollowRejectionDetails;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

// 과거의 데이터를 가져야하는(히스토리성) 데이터는 정규화를 하면 안된다.
/**
 * 고민. 팔로우리스트에 Nickname은 항상 따라올텐데,
 * - 이것을 Follow 테이블에 넣어서 조인으로 가져와야 하나?
 * - 아니면 식별자만 가지고 있다가 쿼리를 날려데이터를 가져올 건가? 하나더 고민해야할 부분. -
 */
/**
 * 데이터의 최신성을 보장해야하나 ?
 * - NickNameHistory는 오히려 최신성을 보장하면 안됐음.
 * - Follow 같은경우는 데이터 최신성을 보장해야함 = 정규화 하기 -> 데이터 중복 제거,
 * 만약 조회의 편리를 위해 필드를 추가한다 하면,
 * 닉네임 변경은 자주 일어남. 100만명의 팔로워를 가지고 있는 인플루언서가 닉네임을 변경했을 경우,
 * 100만개의 레코드를 업데이트 쳐야함. 관리가 쉽지 않음. 그래서 정규화 하는게 좋음.
 *
 */
@Getter
public class Follow {
    private final Long id;
    private final Follower follower;
    private final Following following;
    private final FollowStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    @Builder
    public Follow(Long id, Follower follower, Following following, FollowStatus status, LocalDateTime createdAt,
                  LocalDateTime modifiedAt) {
        if(follower.getId().equals(following.getId())){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.follower = follower;
        this.following = following;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Follow from(FollowCreationDetails creationsDetails) {
        return Follow.builder()
                .follower(creationsDetails.getFromUser())
                .following(creationsDetails.getToFollowing())
                .status(creationsDetails.getStatus())
                .createdAt(creationsDetails.getCreatedAt())
                .modifiedAt(creationsDetails.getModifiedAt())
                .build();
    }

    public Follow accept(FollowAcceptionDetails followAcceptionDetails) {
//        if (!this.status.equals(FollowStatus.ACCEPTED)) {
//            throw new IllegalStateException("도메인 예외 "+followAcceptionDetails.getStatus());
//        }
        return Follow.builder()
                .id(id)
                .follower(follower)
                .following(following)
                .status(followAcceptionDetails.getStatus())
                .createdAt(createdAt)
                .modifiedAt(followAcceptionDetails.getModifiedAt())
                .build();

    }

    public Follow reject(FollowRejectionDetails followRejectionDetails) {
//        if (!this.status.equals(FollowStatus.REJECTED)) {
//            throw new IllegalStateException("도메인 예외 "+followRejectionDetails.getStatus());
//        }
        return Follow.builder()
                .id(id)
                .follower(follower)
                .following(following)
                .status(followRejectionDetails.getStatus())
                .createdAt(createdAt)
                .modifiedAt(followRejectionDetails.getModifiedAt())
                .build();
    }

    public Follow cancel(FollowCancelDetails followCancelDetails) {
//        if (!this.status.equals(FollowStatus.CANCELLED)) {
//            throw new IllegalStateException("도메인 예외 "+followCancelDetails.getStatus());
//        }
        return Follow.builder()
                .id(id)
                .follower(follower)
                .following(following)
                .status(followCancelDetails.getStatus())
                .createdAt(createdAt)
                .modifiedAt(followCancelDetails.getModifiedAt())
                .build();
    }
    public Follow delete(FollowDeleteDetails followDeleteDetails) {
//        if (!this.status.equals(FollowStatus.DELETED)) {
//            throw new IllegalStateException("도메인 예외 "+ followDeleteDetails.getStatus());
//        }
        return Follow.builder()
                .id(id)
                .follower(follower)
                .following(following)
                .status(followDeleteDetails.getStatus())
                .createdAt(createdAt)
                .modifiedAt(followDeleteDetails.getModifiedAt())
                .build();
    }
    public Long getFollowerId(){
        return follower.getId();
    }
    public Long getFollowingId(){
        return following.getId();
    }
}

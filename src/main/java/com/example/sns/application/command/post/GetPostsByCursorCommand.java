package com.example.sns.application.command.post;


import com.example.sns.core.post.domain.entity.CursorRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPostsByCursorCommand {

    private final Long writerId;
    private final CursorRequest cursorRequest;

    @Builder
    public GetPostsByCursorCommand(Long writerId, CursorRequest cursorRequest) {
        this.writerId = writerId;
        this.cursorRequest = cursorRequest;
    }

    public static GetPostsByCursorCommand of(Long writerId, CursorRequest request){
        return GetPostsByCursorCommand.builder()
                .writerId(writerId)
                .cursorRequest(request)
                .build();
    }
}

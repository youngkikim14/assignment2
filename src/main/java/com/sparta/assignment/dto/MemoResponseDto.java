package com.sparta.assignment.dto;

import com.sparta.assignment.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoResponseDto {
    private String title;
    private String username;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.title = memo.getTitle();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
    }
}

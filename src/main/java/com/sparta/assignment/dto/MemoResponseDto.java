package com.sparta.assignment.dto;

import com.sparta.assignment.entity.Memo;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoResponseDto {
    private String title;
    private String username;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }
}

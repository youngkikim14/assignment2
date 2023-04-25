package com.sparta.assignment.dto;

import com.sparta.assignment.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoRequestDto {

    private String title;
    private String contents;

    public MemoRequestDto(Memo memo) {
        this.title = title;
        this.contents = contents;
    }

}

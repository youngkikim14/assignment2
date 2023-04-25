package com.sparta.assignment.entity;


import com.sparta.assignment.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//숫자를 자동으로 더해줌
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;



    public Memo(MemoRequestDto requestDto, Long userId) {

        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userId = userId;

    }

}
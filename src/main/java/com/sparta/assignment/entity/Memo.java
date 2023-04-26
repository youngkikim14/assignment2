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




    public Memo(MemoRequestDto requestDto, String username) {

        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = username;
    }

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
package com.sparta.assignment.controllor;

import com.sparta.assignment.dto.MemoRequestDto;
import com.sparta.assignment.dto.MemoResponseDto;
import com.sparta.assignment.entity.Memo;
import com.sparta.assignment.service.MemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private  final MemoService memoService;

    @GetMapping("/")//메인 페이지 반환
    public ModelAndView home() {
        return new ModelAndView("index");
    }



    @PostMapping("/api/memos")// 쿠키값 헤더 부분에 담긴 토큰과 작성내용 가져오기
    public String createMemo(@RequestBody MemoRequestDto requestDto, HttpServletRequest request){

        return memoService.createMemo(requestDto, request);
    }

    @GetMapping("/api/memos")//전체 조회//주소가 똑같아도 방식(get,post)달라서 주소 같아도 됨(?)
    public List<Memo> getMemos(){
        return memoService.getMemos();
    }

    @GetMapping("/api/memos/{id}")//특정 게시물 조회
    public MemoResponseDto getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }


    @PutMapping("/api/memos/{id}")//수정
    public String updateMemo(@PathVariable Long id,@RequestBody MemoRequestDto requestDto, HttpServletRequest request){
        return memoService.update(id, requestDto, request);//필요한 값을 넣어줌(id, requestDto)
    }

    @DeleteMapping("/api/memos/{id}")//삭제
    public String deleteMemo(@PathVariable Long id,HttpServletRequest request){
        return memoService.deleteMemo(id,request);
    }


}

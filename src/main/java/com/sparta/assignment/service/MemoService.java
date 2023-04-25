package com.sparta.assignment.service;

import com.sparta.assignment.dto.MemoRequestDto;
import com.sparta.assignment.entity.Memo;
import com.sparta.assignment.entity.User;
import com.sparta.assignment.jwt.JwtUtil;
import com.sparta.assignment.repository.MemoRepository;
import com.sparta.assignment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemoRequestDto createMemo(MemoRequestDto requestDto, HttpServletRequest request) { //토큰값 가져와서 검증
        String token = jwtUtil.resolveToken(request); //토큰값
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token); //토큰으로 사용자 정보 가져오기
            } else {
                throw new IllegalArgumentException("Token Erro"); // 에러표시
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow( //토큰이 맞으면 토큰으로 db에서 사용자 정보 조회
                    () -> new IllegalArgumentException("없는 유저입니다")
            );
            Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user.getId()));

            return new MemoRequestDto(memo);
        } else {
            return null;
        }

    }

    @Transactional(readOnly = true) //전체 글 조회
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }


    @Transactional(readOnly = true)
    public Memo getMemo(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다"));
    }


    @Transactional
    public Long update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request); //토큰값
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token); //토큰으로 사용자 정보 가져오기
            } else {
                throw new IllegalArgumentException("Token Erro"); // 에러표시
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow( //토큰이 맞으면 토큰으로 db에서 사용자 정보 조회
                    () -> new IllegalArgumentException("없는 유저입니다")
            );
            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow( // 없는 글 null 처리
                    () -> new NullPointerException("존재하지 않은 게시글입니다.")
            );
            memoRepository.saveAndFlush(new Memo(requestDto, user.getId())); //업데이트가 memo entity에 따로 존재하는 이유를 모르겠음
            return memo.getId(); // 일관성을 위해 memo entity에서 업데이트 삭제 후 덮어 씌울 수 있도록 함. 그런데 덮어쓰기가 되나?
        } else {
            return null;
        }
    }
    //        Memo memo = memoRepository.findById(id).orElseThrow(
//                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
//        );
//
//        // 패스워드가 일치하는지 확인
//        if (requestDto.getPassword() != null && memo.getPassword().equals(requestDto.getPassword())) {
//            memo.update(requestDto);
//        } else {
//            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
//        }
//
//        return memo.getId();


    @Transactional
    public Long deleteMemo(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request); //토큰값
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token); //토큰으로 사용자 정보 가져오기
            } else {
                throw new IllegalArgumentException("Token Erro"); // 에러표시
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow( //토큰이 맞으면 토큰으로 db에서 사용자 정보 조회
                    () -> new IllegalArgumentException("없는 유저입니다")
            );
            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow( // 없는 글 null 처리
                    () -> new NullPointerException("존재하지 않은 게시글입니다.")
            );
            memoRepository.deleteById(id); // null 처리까지 끝난다면 레포지토리에서 해당 id의 내용 삭제
            return memo.getId();
        } else {
            return null;
        }
    }
}

package com.sparta.assignment.service;

import com.sparta.assignment.dto.MemoRequestDto;
import com.sparta.assignment.entity.Memo;
import com.sparta.assignment.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);

        return memo;
    }


    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return  memoRepository.findAllByOrderByModifiedAtDesc();
    }


    @Transactional(readOnly = true)
    public Memo getMemo(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
    }


    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );

        // 패스워드가 일치하는지 확인
        if (requestDto.getPassword() != null && memo.getPassword().equals(requestDto.getPassword())) {
            memo.update(requestDto);
        } else {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        return memo.getId();
    }

    @Transactional
    public Long deleteMemo(Long id,MemoRequestDto requestDto) {
//        memoRepository.deleteById(id);
        Memo memo = memoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        if (requestDto.getPassword() != null && memo.getPassword().equals(requestDto.getPassword())) {
            memoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        return id;
    }


}

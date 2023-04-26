package com.sparta.assignment.repository;


import com.sparta.assignment.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();

    Optional<Memo> findByIdAndUsername(Long id, String username);
}
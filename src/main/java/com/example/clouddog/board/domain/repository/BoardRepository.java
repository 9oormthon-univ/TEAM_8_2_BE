package com.example.clouddog.board.domain.repository;

import com.example.clouddog.board.domain.Board;
import com.example.clouddog.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
//    List<Board> findByBoardTag(int tagNumber);
//
//    Optional<Board> findByMember(Member member);

//    @Query("select b " +
//            "from Board b " +
//            "where b.member = :member "
//    )
//    Page<Board> findByMember(@Param("member") Member member, PageRequest pageRequest);

}

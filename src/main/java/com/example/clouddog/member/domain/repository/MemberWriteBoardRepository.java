package com.example.clouddog.member.domain.repository;

import com.example.clouddog.member.domain.Member;
import com.example.clouddog.member.domain.MemberWriteBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberWriteBoardRepository extends JpaRepository<MemberWriteBoard, Long> {
    Page<MemberWriteBoard> findMemberWriteBoardsByMember(Member member, PageRequest pageRequest);
    Page<MemberWriteBoard> findByMemberAndTag(Member member, int tag, PageRequest pageRequest);
}

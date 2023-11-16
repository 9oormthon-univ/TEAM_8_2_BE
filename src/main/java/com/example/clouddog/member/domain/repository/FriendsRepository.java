package com.example.clouddog.member.domain.repository;

import com.example.clouddog.member.domain.Friendship;
import com.example.clouddog.member.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository<Friendship, Long> {
    boolean existsByMemberAndFriend(Member member, Member friend);

    List<Friendship> findByMember(Member member);
}

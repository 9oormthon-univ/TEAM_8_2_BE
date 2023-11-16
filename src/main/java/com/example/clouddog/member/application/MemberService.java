package com.example.clouddog.member.application;

import com.example.clouddog.member.api.dto.request.MemberProfileUpdateReqDto;
import com.example.clouddog.member.api.dto.respnse.MemberResDto;
import com.example.clouddog.member.api.dto.respnse.MembersResDto;
import com.example.clouddog.member.domain.Member;
import com.example.clouddog.member.domain.repository.FriendsRepository;
import com.example.clouddog.member.domain.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final FriendsRepository friendsRepository;

    public MemberService(MemberRepository memberRepository, FriendsRepository friendsRepository) {
        this.memberRepository = memberRepository;
        this.friendsRepository = friendsRepository;
    }

    // 메인에서 멤버(본인) 응답 -> 어차피 헤더에 엑세스토큰이 들어오므로 토큰에서 검증 후 이메일로 멤버 찾아서 반환하는 로직임.
    public MemberResDto myInfo(String email) {
        return MemberResDto.of(memberRepository.findByEmail(email).orElseThrow());
    }

    // 멤버 프로필 저장, 업데이트.
    @Transactional
    public void myProfileUpdate(Long memberId, MemberProfileUpdateReqDto memberProfileUpdateReqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.profileUpdate(memberProfileUpdateReqDto);
    }

    public MembersResDto membersInformation() {
        List<Member> members = memberRepository.findAll();

        List<MemberResDto> memberResDtos = new ArrayList<>();
        for (Member member : members) {
            memberResDtos.add(MemberResDto.of(member));
        }

        return MembersResDto.of(memberResDtos);
    }
}

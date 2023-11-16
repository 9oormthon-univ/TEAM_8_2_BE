package com.example.clouddog.member.api;

import com.example.clouddog.member.api.dto.request.MemberProfileUpdateReqDto;
import com.example.clouddog.member.api.dto.respnse.MemberResDto;
import com.example.clouddog.member.api.dto.respnse.MembersResDto;
import com.example.clouddog.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/success")
    public ResponseEntity<String> loginSuccess() {
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/my-info")
    public ResponseEntity<MemberResDto> myInfo(@RequestParam(name = "email") String email) {
        return new ResponseEntity<>(memberService.myInfo(email), HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<String> myProfileUpdate(@PathVariable(name = "memberId") Long memberId,
                                                  @RequestBody MemberProfileUpdateReqDto memberProfileUpdateReqDto) {
        memberService.myProfileUpdate(memberId, memberProfileUpdateReqDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<MembersResDto> membersInfo() {
        return new ResponseEntity<>(memberService.membersInformation(), HttpStatus.OK);
    }

}

package com.example.clouddog.member.api;

import com.example.clouddog.member.api.dto.respnse.MembersResDto;
import com.example.clouddog.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/info")
    public ResponseEntity<MembersResDto> membersInfo() {
        MembersResDto membersResDto = memberService.membersInformation();
        return new ResponseEntity<>(membersResDto, HttpStatus.OK);
    }

}

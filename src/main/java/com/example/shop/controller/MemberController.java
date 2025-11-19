package com.example.shop.controller;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.member.MemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("${api.v1}/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository; // 생성자 안써도 자동으로

    @Operation(
            summary = "회원 목록 조회",
            description = "public.member 테이블에 저장된 모든 회원을 조회한다."
    )
    @GetMapping
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Operation(
            summary = "회원 등록",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 저장한다."
    )
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody MemberRequest request) {

        Member member = new Member(
                UUID.randomUUID(),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        Member member1 = memberRepository.save(member);

        AtomicInteger cnt;
        if(member1 instanceof Member) {
            cnt=((List<?>) member1).size();
        } else {
            cnt = new AtomicInteger();
            if(member1 instanceof Iterable) {
                ((Iterable<Member>) member1).forEach(item -> cnt.getAndIncrement());
            }
        }

        return new ResponseEntity<>(HttpStatus.OK.value(), member1, cnt.get());
    }

    @Operation(
            summary = "회원 수정",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 수정한다."
    )
    @PutMapping("{id}")
    public Member update(@RequestBody MemberRequest request, @PathVariable String id) {
        Member member = new Member(
                id,
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        return memberRepository.save(member);
    }

    @Operation(
            summary = "회원 삭제",
            description = "요청으로 받은 회원 정보를 public.member 테이블에서 삭제한다."
    )
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        memberRepository.deleteById(UUID.fromString(id));
    }

}

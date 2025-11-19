package com.example.shop.member;

public record MemberRequest(
        String email,
        String name,
        String password,
        String phone,
        String saltKey,
        String flag
) {
}

// final private를 자동으로 처리해줌
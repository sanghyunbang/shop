package com.example.shop.common;

public class ResponseEntity<T> {
    private final int status;
    private final T data;
    private final int count;

    public ResponseEntity(int status, T all, int count) {
        this.status = status;
        this.data = all;
        this.count = count;
    }
}

package com.example.demo.dto;

public class Result {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result(String name, String code) {
        this.name = name;
        this.code = code;
    }
}

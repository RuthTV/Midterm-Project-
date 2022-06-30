package com.ironhack.Midterm.Project.controller.user.dto;

public class ThirdPartyDTO {
    private String name;

    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

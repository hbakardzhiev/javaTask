package com.example.demo.modules;

public enum MarkupState {
    ON("on"),
    OFF("off");

    private String state;

    MarkupState(String inputState) {
        this.state = inputState;
    }

    public String getState() {
        return this.state;
    }
}

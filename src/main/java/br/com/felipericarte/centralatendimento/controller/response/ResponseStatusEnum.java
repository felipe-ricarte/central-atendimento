package br.com.felipericarte.centralatendimento.controller.response;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {
    SUCCESS("success"),
    ERROR("error"),
    INFO("info"),
    WARNING("warning");

    private final String description;

    ResponseStatusEnum(String description) {
        this.description = description;
    }
}

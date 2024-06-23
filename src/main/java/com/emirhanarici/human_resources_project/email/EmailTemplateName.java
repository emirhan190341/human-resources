package com.emirhanarici.human_resources_project.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate-account");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}

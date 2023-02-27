package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    EMAIL("Электронная почта"),
    SKYPE("Логин Skype"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

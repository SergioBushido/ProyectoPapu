package es.comicon.comic.models.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    REGISTER_USER_READ("register_user:read"),
    REGISTER_USER_UPDATE("register_user:update"),
    REGISTER_USER_CREATE("register_user:create"),
    REGISTER_USER_DELETE("register_user:delete");

    @Getter
    private final String permission;
}

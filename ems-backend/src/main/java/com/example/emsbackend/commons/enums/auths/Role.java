package com.example.emsbackend.commons.enums.auths;

public enum Role {

    ADMIN("ADMIN", 1L),
    EDITOR("EDITOR", 2L),
    VIEWER("VIEWER", 3L);
    private final String value;
    private final Long id;

    Role(String value, Long id) {
        this.value = value;
        this.id = id;
    }


    // Static method to retrieve enum constant by value
    public static String fromId(Long id) {
        for (Role role : Role.values()) {
            if (role.id.equals(id)) {
                return role.value;
            }
        }
        throw new IllegalArgumentException("Unknown role id: " + id);
    }


}

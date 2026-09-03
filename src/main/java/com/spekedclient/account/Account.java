package com.spekedclient.account;

public class Account {
    public enum AccountType {
        MICROSOFT("Microsoft"),
        ELY_BY("Ely.by"),
        LITTLE_SKIN("LittleSkin"),
        OFFLINE("Offline");

        private final String displayName;

        AccountType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private final String uuid;
    private final String username;
    private final AccountType type;
    private String accessToken;
    private String refreshToken;
    private long expiresAt;
    private String skinUrl;
    private boolean active;

    public Account(String uuid, String username, AccountType type) {
        this.uuid = uuid;
        this.username = username;
        this.type = type;
        this.active = false;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public AccountType getType() {
        return type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getSkinUrl() {
        return skinUrl;
    }

    public void setSkinUrl(String skinUrl) {
        this.skinUrl = skinUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAt;
    }
}

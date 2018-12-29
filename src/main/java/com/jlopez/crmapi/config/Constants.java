package com.jlopez.crmapi.config;

public class Constants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "c41d8cd98f21b204e9d00998ecf8427f";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "x-access-token";
    public static final String AUTHORITIES_KEY = "scopes";
}

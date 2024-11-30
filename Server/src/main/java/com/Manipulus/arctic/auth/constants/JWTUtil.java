package com.Manipulus.arctic.auth.constants;

public class JWTUtil {
    public static final long EXPIRE_ACCESS_TOKEN = 10*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 120*60*1000;
    public static final String ISSUER = "arcticApplication";
    public static final String SECRET = "arcticSecret";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
}

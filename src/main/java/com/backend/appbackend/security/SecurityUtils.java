package com.backend.appbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public final class SecurityUtils {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign-up";
    public static final String VIEW_JOBS_URL = "/jobs/all";
    public static final String VIEW_STORIES_URL = "/stories";
    public static final String VIEW_STORY_IMAGES_URL = "/story/{id}/images";

    public static final DecodedJWT getDecodedToken(String token) {
        //#TODO jei bad request tai programa luzta tai not that great
        String[] tokenParts = token.split(" ");
        token = tokenParts[1];
        return JWT.decode(token);
    }

    public static final String getEmailFromToken(String token) {
        DecodedJWT decodedToken = getDecodedToken(token);
        return decodedToken.getClaim("sub").asString();
    }
}

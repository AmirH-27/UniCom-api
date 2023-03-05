package com.unicomapi.annotation;

import com.unicomapi.auth.AuthenticationResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("isAuthenticated()")
@Documented
public @interface CurrentUser {
}

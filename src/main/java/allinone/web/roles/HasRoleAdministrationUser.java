package allinone.web.roles;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasRole('ROLE_ADMINISTRATION_USER')")
public @interface HasRoleAdministrationUser {
}

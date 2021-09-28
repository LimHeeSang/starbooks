package startproject.starbooks.domain.account;

import lombok.Getter;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Getter
public enum UserRole {
    ROLE_GUEST("ROLE_GUEST"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}

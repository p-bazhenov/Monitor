package monitor.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, PLAYER, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}

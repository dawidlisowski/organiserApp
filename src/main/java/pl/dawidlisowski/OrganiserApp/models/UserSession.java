package pl.dawidlisowski.OrganiserApp.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.dawidlisowski.OrganiserApp.models.entities.UserEntity;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserSession {
    private boolean isLoggedIn;
    private UserEntity userEntity;
}

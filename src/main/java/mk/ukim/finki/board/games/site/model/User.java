package mk.ukim.finki.board.games.site.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.board.games.site.model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "app_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String avatarUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Favourites> favourites;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private boolean enabled = true;

    public User(String username, String password, String name, String surname, String email, String phone, String avatarUrl, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

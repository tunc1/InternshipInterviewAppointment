package app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    private String username;
    private String role;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean accountNonExpired=true,accountNonLocked=true,enabled=true,credentialsNonExpired=true;
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }
    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired)
    {
        this.accountNonExpired=accountNonExpired;
    }
    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked=accountNonLocked;
    }
    public boolean isEnabled()
    {
        return enabled;
    }
    public void setEnabled(boolean enabled)
    {
        this.enabled=enabled;
    }
    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        this.credentialsNonExpired=credentialsNonExpired;
    }
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role=role;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
}

package io.codeagainsthumanity.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    // instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;



    @Column(unique=true)
    String username;
    String password;
    String fullName;

    //setting up relationships with other models
    @ManyToOne
    ApplicationUser player;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "gamesPlayed",
            joinColumns = @JoinColumn(name="player", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="game", referencedColumnName = "id")
            )
    List<Game> myGames;

    // constructors
    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    // getters
    public String getFullName() {
        return fullName;
    }

    // interface methods

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}

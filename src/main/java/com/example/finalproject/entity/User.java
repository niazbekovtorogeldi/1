package com.example.finalproject.entity;

import com.example.finalproject.enam.Role;
import com.example.finalproject.validation.EmailValid;
import com.example.finalproject.validation.PasswordValid;
import com.example.finalproject.validation.PhoneNumberValid;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    @NotNull(message = "Поле должно быть заполнено")
    private String firstName;
    @NotNull(message = "Поле должно быть заполнено")
    private String lastName;
    @NotNull(message = "Поле должно быть заполнено")
    private ZonedDateTime dateOfBirth;
    @NotNull(message = "Поле должно быть заполнено")
    @EmailValid
    private String email;
    @NotNull(message = "Поле должно быть заполнено")
    @PasswordValid
    private String password;
    @Value("phone_number")
    @PhoneNumberValid
    private String phoneNumber;
    @NotNull(message = "Поле должно быть заполнено")
    @Enumerated(EnumType.STRING)
    private Role role;
    private int age;
    private int expirense;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private Restaurant restaurant;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "user")
    private List<Cheque> cheques;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

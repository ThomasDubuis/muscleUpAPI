package com.ynov.muscleup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.muscleup.model.customer_args.Gender;
import com.ynov.muscleup.model.customer_args.Role;
import com.ynov.muscleup.model.customer_args.Visibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    @JsonIgnore
    private String hashedPassword;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Role role;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    private Double armSize;
    private Double chestSize;
    private Double waistSize;
    private Double thighSize;
    private Double penisSize;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void updateIfNotNull(Customer customer) {
        if (customer.firstname != null) {
            firstname = customer.firstname;
        }
        if (customer.lastname != null) {
            lastname = customer.lastname;
        }
        if (customer.email != null) {
            email = customer.email;
        }
        if (customer.visibility != null) {
            visibility = customer.visibility;
        }
        if (customer.armSize != null) {
            armSize = customer.armSize;
        }
        if (customer.chestSize != null) {
            chestSize = customer.chestSize;
        }
        if (customer.waistSize != null) {
            waistSize = customer.waistSize;
        }
        if (customer.thighSize != null) {
            thighSize = customer.thighSize;
        }
        if (customer.penisSize != null) {
            penisSize = customer.penisSize;
        }
        if (customer.gender != null) {
            gender = customer.gender;
        }
    }


}

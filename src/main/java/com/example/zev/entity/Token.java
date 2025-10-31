package com.example.zev.entity;


import com.example.zev.constants.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Token")
@Table(name = "tokens")
@Data
@FieldNameConstants
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseEntity{

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.EAGER)
    public User user;
}

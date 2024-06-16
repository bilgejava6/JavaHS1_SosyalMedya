package com.muhammet.entity;

import com.muhammet.utility.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tblauth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true,length = 64,updatable = false)
    String userName;
    @Column(length = 32)
    String password;
    @Column(length = 138)
    String email;
    @Enumerated(EnumType.STRING)
    State state;
}

package com.effective_mobile.socialmedia.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @Column(name = "e-mail")
    private String email;

    @Column(name = "role")
    @Enumerated (value = EnumType.STRING)
    private String role;

    @OneToMany (mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Post> posts;
}

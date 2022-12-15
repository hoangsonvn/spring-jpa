package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NamedEntityGraph(name = "UserEntity.roles",
        attributeNodes = @NamedAttributeNode("roles")
)
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;
    @Column
    private String password;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"),// master
            inverseJoinColumns = @JoinColumn(name = "roleid"))// tạo bảng trung gian user_rolo 2 cột userid và roleid
    private List<RoleEntity> roles = new ArrayList<>();

    public void addRole(RoleEntity role) {
        roles.add(role);
        role.getUsers().add(this); // có hay không ở bảng cha thì hình như khôgn ảnh hưởng,  vẫn save tốt  "vừa thửlần 2 ngày 24/11
    }

    public void removeRole(RoleEntity role) {
        roles.remove(role); // lệnh từ bảng trung gian này selecte role vs  user_role có userId = ?
        role.getUsers().remove(this);// vì thằng role này là many to many có thể còn chưa thằng user khác // lệnh từ bảng trung gian này selecte user vs  user_role có roleId = ?
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
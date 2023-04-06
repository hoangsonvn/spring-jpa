package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "role")
@Entity

public class RoleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles",cascade = {CascadeType.PERSIST, CascadeType.MERGE})// thằng này là slave
    private List<UserEntity> users = new ArrayList<>();


    public void removeUser(UserEntity user) {
        users.remove(user); // lệnh từ bảng trung gian này selecte role vs  user_role có userId = ?
        user.getRoles().remove(this);// vì thằng role này là many to many có thể còn chưa thằng user khác // lệnh từ bảng trung gian này selecte user vs  user_role có roleId = ?
    }  // removeUser không hoạt động
    @PreRemove
    private void removeRolesFromUsers() {
        for (UserEntity u : users) {// 1 câu lẹnh lấy ra users từ userId
            u.getRoles().remove(this);// để remove sẽ find lên những role của userid
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}

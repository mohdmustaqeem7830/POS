package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String username);
}

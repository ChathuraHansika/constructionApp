package com.akvasoft.construction.repo;

import com.akvasoft.construction.entity.User;
import com.akvasoft.construction.util.DomainConstant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserNameAndStatus(String username, Status status);

    User findByUserName(String user);

}

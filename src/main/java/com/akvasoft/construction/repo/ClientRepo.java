package com.akvasoft.construction.repo;

import com.akvasoft.construction.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

    Client findByTelEquals(String tel);

    Client findClientByNameEquals(String s);
}

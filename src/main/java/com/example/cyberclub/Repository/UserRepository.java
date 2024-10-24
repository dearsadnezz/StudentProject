package com.example.cyberclub.Repository;

import com.example.cyberclub.Data.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> { Users findByUsername(String username); }

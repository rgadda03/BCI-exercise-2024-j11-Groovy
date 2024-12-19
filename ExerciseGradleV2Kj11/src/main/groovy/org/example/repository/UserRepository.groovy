package org.example.repository

import org.example.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<UserEntity, UUID>{

}
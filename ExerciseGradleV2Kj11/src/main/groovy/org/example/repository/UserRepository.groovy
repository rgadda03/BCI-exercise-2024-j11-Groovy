package org.example.repository

import org.example.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<UserEntity, UUID>{
    UserEntity findByEmail(String email)
}
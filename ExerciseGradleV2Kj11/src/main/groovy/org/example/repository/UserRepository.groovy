package org.example.repository

import org.example.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import javax.transaction.Transactional
import java.time.LocalDate

@Repository
interface UserRepository extends JpaRepository<UserEntity, UUID>{

    UserEntity findByEmail(String email)
    
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity SET token = :token, last_login = :lastLogin WHERE email = :email")
    void actualizarTokenYLastLoginPorEmail(@Param("token") String token, @Param("lastLogin") LocalDate lastLogin, @Param("email") String email)

}
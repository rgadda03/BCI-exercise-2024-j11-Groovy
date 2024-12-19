package org.example.repository

import org.example.entity.PhoneEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneRepository extends JpaRepository<PhoneEntity, UUID>{

}

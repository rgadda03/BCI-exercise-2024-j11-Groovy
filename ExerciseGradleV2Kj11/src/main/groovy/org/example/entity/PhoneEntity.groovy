package org.example.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "phones")
class PhoneEntity {

    private static final long serialVersionUID = 4088727029680737417L;

    @Id
    @Column (name = "phone_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID phone_id;

    @Column (name = "number")
    private Long number;

    @Column (name = "city_code")
    private int city_code;

    @Column (name = "country_code")
    private String country_code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "PhoneEntity [phone_id=" + phone_id + ", number=" + number + ", city_code=" + city_code+ ", country_code=" + country_code + "]";
    }

    static long getSerialVersionUID() {
        return serialVersionUID
    }

    UUID getPhone_id() {
        return phone_id
    }

    void setPhone_id(UUID phone_id) {
        this.phone_id = phone_id
    }

    Long getNumber() {
        return number
    }

    void setNumber(Long number) {
        this.number = number
    }

    int getCity_code() {
        return city_code
    }

    void setCity_code(int city_code) {
        this.city_code = city_code
    }

    String getCountry_code() {
        return country_code
    }

    void setCountry_code(String country_code) {
        this.country_code = country_code
    }

    UserEntity getUser() {
        return user
    }

    void setUser(UserEntity user) {
        this.user = user
    }
}

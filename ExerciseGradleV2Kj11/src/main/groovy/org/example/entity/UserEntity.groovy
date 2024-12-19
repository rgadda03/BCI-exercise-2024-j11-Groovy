package org.example.entity

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springframework.lang.Nullable

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.LocalDate

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "users")
class UserEntity {

    private static final long serialVersionUID = 4088727029680737417L;

    @Id
    @Column (name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id; //UUID s232s-sadas2-2321sads-etc

    @Column (name = "name")
    @Nullable
    private String name;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "created")
    private LocalDate created;

    @Column (name = "last_login")
    private LocalDate last_login;

    @Column (name = "token")
    private String token;

    @Column (name = "isactive")
    private boolean isactive;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "user",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<PhoneEntity> phones;

    @Override
    public String toString() {

        String phoneString = "phones= (";

        if (phones!=null) {
            for (Iterator iterator = phones.iterator(); iterator.hasNext();) {
                PhoneEntity phoneEntity = (PhoneEntity) iterator.next();

                phoneString+= phoneEntity.toString()+",";
            }
        }
        phoneString+=") ";

        return "UserEntity [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
        + ", created=" + created + ", last_login=" + last_login + ", token=" + token + ", isactive=" + isactive
        + ", "+ phoneString + "]";
    }

    static long getSerialVersionUID() {
        return serialVersionUID
    }

    UUID getUser_id() {
        return user_id
    }

    void setUser_id(UUID user_id) {
        this.user_id = user_id
    }

    @Nullable
    String getName() {
        return name
    }

    void setName(@Nullable String name) {
        this.name = name
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    LocalDate getCreated() {
        return created
    }

    void setCreated(LocalDate created) {
        this.created = created
    }

    LocalDate getLast_login() {
        return last_login
    }

    void setLast_login(LocalDate last_login) {
        this.last_login = last_login
    }

    String getToken() {
        return token
    }

    void setToken(String token) {
        this.token = token
    }

    boolean getIsactive() {
        return isactive
    }

    void setIsactive(boolean isactive) {
        this.isactive = isactive
    }

    List<PhoneEntity> getPhones() {
        return phones
    }

    void setPhones(List<PhoneEntity> phones) {
        this.phones = phones
    }
}

package EcomUserAuthServiceFinal.EcomUserAuthServiceFinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "ECOM_USER")//user is reserve keyword
public class User extends BaseModel {
    private String name;
    private String emailId;
    private String password; // wont store actual password
    private String token; // temporary solution

    @ManyToMany
    private List<Role> roles;
}
package preproject.kata314.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true, nullable = false)
  private String email;
  private String password;
  private int age;
  private String firstName;
  private String lastName;

  @ManyToMany(fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public User() {
  }

  public User(String email, String password, int age, String firstName, String lastName) {
    this.email = email;
    this.password = password;
    this.age = age;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public Set<Role> getRoles() {
    if (roles == null) {
      roles = new HashSet<>();
    }
    return roles;
  }

  public void setRoles(Set<Role> roleSet) {
    this.roles = roleSet;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id == user.id && age == user.age && Objects.equals(email, user.email) && Objects.equals(
        password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(
        lastName, user.lastName) && Objects.equals(roles, user.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, age, firstName, lastName, roles);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\''
        + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
        + ", roles=" + roles + '}';
  }
}

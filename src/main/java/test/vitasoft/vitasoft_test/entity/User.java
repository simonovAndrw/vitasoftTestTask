package test.vitasoft.vitasoft_test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "employeesRoles",
            joinColumns = {
                    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, updatable = true)},
            inverseJoinColumns = {
                    @JoinColumn(name = "roleId", referencedColumnName = "id",
                            nullable = false, updatable = true)})
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private List<Report> reports = new LinkedList<>();

    public User() {}

    public User(String name, String employeePassword) {
        this.name = name;
        this.password = employeePassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String employeeName) {
        this.name = employeeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String employeePassword) {
        this.password = employeePassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}

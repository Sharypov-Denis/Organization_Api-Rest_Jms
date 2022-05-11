package sharypov.OrganizationRestApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sharypov.OrganizationRestApi.model.error.RestError;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Сущность Организация
 */
@Entity
@Table(name = "organization")
public class Organization extends RestError {

    public static final int START_SEQ = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    private String name;

    @Size(min = 10, message = "Длина ИНН не может быть менее 10 символов")
    private String inn;

    @JsonManagedReference
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonIgnore
    private List<Account> accounts;

    public Organization() {
    }

  /*  public Organization(String name, String inn) {
        this(null, name, inn);
    }

    public Organization(Long id, String name, String inn) {
        this.id = id;
        this.name = name;
        this.inn = inn;
    }*/

    public static int getStartSeq() {
        return START_SEQ;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

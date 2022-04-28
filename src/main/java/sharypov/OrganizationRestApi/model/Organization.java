package sharypov.OrganizationRestApi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Сущность Организация
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "organization")
public class Organization extends RestError {

    public static final int START_SEQ = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)

    private Long id;
    private String name;
    private String inn;

    public Organization() {
    }

    public Organization(String name, String inn) {
        this(null, name, inn);
    }

    public Organization(Long id, String name, String inn) {
        this.id = id;
        this.name = name;
        this.inn = inn;
    }
}

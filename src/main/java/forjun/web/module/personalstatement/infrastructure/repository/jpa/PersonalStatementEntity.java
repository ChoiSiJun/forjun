package forjun.web.module.personalstatement.infrastructure.repository.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonalStatementEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @NotNull
    private String type;
    private String data;
    private int displayNo;
    private String link;
}

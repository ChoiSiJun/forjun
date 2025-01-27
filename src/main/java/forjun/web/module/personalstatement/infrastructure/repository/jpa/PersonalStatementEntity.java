package forjun.web.module.personalstatement.infrastructure.repository.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="PersonalStatement")
public class PersonalStatementEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @NotNull
    private String type;

    @NotNull
    private String title;

    private String data;
}

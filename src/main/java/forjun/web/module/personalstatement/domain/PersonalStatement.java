package forjun.web.module.personalstatement.domain;

import forjun.web.module.personalstatement.infrastructure.repository.jpa.PersonalStatementEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PersonalStatement {

    private int id;
    private String type;
    private String group_type;
    private String label;
    private String data;

    public PersonalStatementEntity toEntity(){

        return PersonalStatementEntity.builder()
                .id(this.id)
                .type(this.type)
                .label(this.label)
                .data(this.data)
                .build();
    }

    public static PersonalStatement fromEntity(PersonalStatementEntity entity){

        return PersonalStatement.builder()
                .id(entity.getId())
                .type(entity.getType())
                .label(entity.getLabel())
                .data(entity.getData())
                .build();
    }
}

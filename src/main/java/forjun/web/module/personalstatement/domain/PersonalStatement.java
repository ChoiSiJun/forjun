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
    private String title;
    private String data;

    public PersonalStatementEntity toEntity(){

        return PersonalStatementEntity.builder()
                .id(this.id)
                .type(this.type)
                .title(this.title)
                .data(this.data)
                .build();
    }

    public static PersonalStatement fromEntity(PersonalStatementEntity entity){

        return PersonalStatement.builder()
                .id(entity.getId())
                .data(entity.getData())
                .type(entity.getType())
                .title(entity.getTitle())
                .build();

    }
}

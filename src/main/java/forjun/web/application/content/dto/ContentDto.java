package forjun.web.application.content.dto;

import forjun.web.domain.content.ContentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContentDto {

    private int id;

    private String title;

    private String content;

    private String author;

    private LocalDateTime newDate;

    private LocalDateTime editDate;

    public static ContentDto fromEntity(ContentEntity contentEntity) {

        return ContentDto.builder()
                .id(contentEntity.getId())
                .title(contentEntity.getTitle())
                .content(contentEntity.getContent())
                .author(contentEntity.getAuthor())
                .newDate(contentEntity.getNewDate())
                .editDate(contentEntity.getEditDate())
                .build();
    }

    public ContentEntity toEntity(){
        return ContentEntity.builder()
                .id(this.id)
                .title(this.title)
                .author(this.author)
                .content(this.content)
                .build();
    }
}



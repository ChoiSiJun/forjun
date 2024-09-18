package forjun.web.application.content;

import forjun.web.application.content.dto.ContentDto;
import forjun.web.domain.content.ContentEntity;
import forjun.web.domain.content.ContentRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService
{
    private final ContentRepo contentRepo;

    //콘텐츠 저장
    public ContentDto setContent(ContentDto contentDto){
        contentRepo.save(contentDto.toEntity());
        return contentDto;
    }

    //콘텐츠 업데이트
    ContentDto updateContent(ContentDto contentDto){

        ContentEntity contentEntity = contentRepo.findById(contentDto.getId()).orElseThrow(() -> new EntityNotFoundException("Content not Found"));
        contentEntity.updateContent()
                .title(contentDto.getTitle())
                .author(contentDto.getAuthor())
                .content(contentDto.getContent())
                .build();
        contentRepo.save(contentEntity);

        return ContentDto.fromEntity(contentEntity);
    }

    //콘텐츠 정보 가져오기
    public ContentDto getContentById(int id) {
        ContentEntity contentEntity = contentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Content not Found"));
        return ContentDto.fromEntity(contentEntity);
    }
    
    //콘텐츠 리스트 가져오기
    public List<ContentDto> getAllContents() {
        return  contentRepo.findAll().stream()
                .map(ContentDto::fromEntity)
                .collect(Collectors.toList());
    }
}

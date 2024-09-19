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



    //콘텐츠 저장 & 업데이트
    ContentDto setContent(ContentDto contentDto){
        ContentEntity contentEntity = contentRepo.findById(contentDto.getId())
                        .map(content -> {
                            content.updateContent()
                                    .title(contentDto.getTitle())
                                    .contentType(contentDto.getContentType())
                                    .content(contentDto.getContent())
                                    .author(contentDto.getAuthor())
                                    .build();
                            return content;
                        }).orElse(contentDto.toEntity());

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

    //콘텐츠 삭제하기
    public void deleteContent(int contentId){
        try {
            contentRepo.deleteById(contentId);
        }catch(Exception e){
            throw new RuntimeException("Delete Content Failed");
        }

    }
}

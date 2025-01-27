package forjun.web.module.content.infrastructure.adapter;

import forjun.web.exception.infrastructure.JpaException;
import forjun.web.module.content.application.port.ContentPort;
import forjun.web.module.content.domain.Content;
import forjun.web.module.content.infrastructure.repository.jpa.ContentEntity;
import forjun.web.module.content.infrastructure.repository.jpa.ContentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class ContentJpaAdapter implements ContentPort {

    private final ContentRepo contentRepo;
    @Override
    public void saveContent(Content content) {
        try {
            contentRepo.save(content.toEntity());
        }catch (Exception e){
            if(content.getId() == 0){
                throw new JpaException("콘텐츠 생성 실패","Content","insert",e.getCause());
            }else{
                throw new JpaException("콘텐츠 수정 실패","Content","update",e.getCause());
            }
        }
    }

    @Override
    public Content getContent(int contentId) {
        try {
            Optional<ContentEntity> contentEntity = contentRepo.findById(contentId);
            return contentEntity.map(Content::fromEntity).orElse(null);
        }catch (Exception e){
            throw new JpaException("콘텐츠 조회에 실패하였습니다.", "Content", "find", e.getCause());
        }
    }

    @Override
    public List<Content> getContents() {
        try {
            return  contentRepo.findAll().stream()
                    .map(Content::fromEntity)
                    .collect(Collectors.toList());

        }catch (Exception e){
            throw new JpaException("콘텐츠 리스트 조회에 실패하였습니다.", "Content", "findAll", e.getCause());
        }
    }

    @Override
    public void deleteContent(int contentId) {
        try {
            contentRepo.deleteById(contentId);
        } catch (Exception e) {
            throw new JpaException("콘텐츠 삭제에 실패하였습니다.", "Content", "delete", e.getCause());
        }
    }
}

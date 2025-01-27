package forjun.web.module.content.application;

import forjun.web.exception.application.content.ContentDuplicateException;
import forjun.web.exception.application.content.ContentNotFoundException;
import forjun.web.module.content.domain.Content;
import forjun.web.module.content.infrastructure.adapter.ContentJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService
{
    private final ContentJpaAdapter contentJpaAdapter;

    //콘텐츠 저장
    public void saveContent(Content content){

        if(content.getId() > 0){
            if(contentJpaAdapter.getContent(content.getId()) != null){
                throw new ContentDuplicateException();
            }
        }

        contentJpaAdapter.saveContent(content);
    }

    //콘텐츠 수정
    public void updateContent(Content content){

        if(contentJpaAdapter.getContent(content.getId()) == null){
            throw new ContentNotFoundException();
        }
        contentJpaAdapter.saveContent(content);

    }


    //콘텐츠 정보 가져오기
    public Content getContentById(int contentId) {

        Content content = contentJpaAdapter.getContent(contentId);
        if(content == null){throw new ContentNotFoundException();}

        return content;
    }
    
    //콘텐츠 리스트 가져오기
    public List<Content> getAllContents() {

        List<Content> contents = contentJpaAdapter.getContents();
        if(contents.isEmpty()){
            throw new ContentNotFoundException();
        }
        return contents;
    }

    //콘텐츠 삭제하기
    public void deleteContent(int contentId){
        contentJpaAdapter.deleteContent(contentId);
    }
}

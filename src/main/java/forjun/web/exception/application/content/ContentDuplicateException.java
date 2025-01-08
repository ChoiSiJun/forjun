package forjun.web.exception.application.content;

public class ContentDuplicateException extends RuntimeException{

    public ContentDuplicateException(){
        super("이미 존재하는 컨텐츠 입니다.");
    }

}

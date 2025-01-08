package forjun.web.exception.application.content;

public class ContentNotFoundException extends RuntimeException{

    public ContentNotFoundException(){
        super("콘텐츠 정보를 찾을수 없습니다.");
    }
}

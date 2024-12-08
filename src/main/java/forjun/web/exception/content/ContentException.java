package forjun.web.exception.content;

public class ContentException extends RuntimeException{

    public ContentException(String message) {
        super("Content Exception : " + message);
    }
}

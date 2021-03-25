package technomarket.exeptions;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String msg){
        super(msg);
    }
}

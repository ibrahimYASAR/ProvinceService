package provinceservice.exception;

public class ProvinceNotFoundException extends RuntimeException{
    public ProvinceNotFoundException(String message){
        super(message);
    }
}

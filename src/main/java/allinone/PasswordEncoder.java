package allinone;

public class PasswordEncoder extends org.springframework.security.authentication.encoding.ShaPasswordEncoder {
    
    public PasswordEncoder() {
    }
    
    @Override
    public String encodePassword(String originalPassword, Object salt) {
        // here supply salt = username + saltString
        System.out.println("ENCODING");
        String encryptedPassword = super.encodePassword(originalPassword, salt);
        return encryptedPassword;
    }
    
}

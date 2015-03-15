package allinone.web.controller.utils;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Klasa zawiera wiadomość wykorzystywaną przy przekierowaniach w kontrolerach.
 *
 * @author jbela
 */
public class Message {
    
    public enum Type {
        INFO, SUCCESS, WARNING, DANGER
    }
    
    // Typ wiadomości
    private Type   type;
    
    // Treść wiadomości
    private String content;
    
    // Jeśli ustawiono to automatycznie ukrywana wiadomość po wskazanym czasie
    private Long   autoHideMillis;
    
    private Message() {
    }
    
    public static Message get(Type type) {
        assertThat(type).isNotNull();
        
        Message m = new Message();
        m.type = type;
        
        return m;
    }
    
    public static Message getDanger() {
        return get(Type.DANGER);
    }
    
    public static Message getSuccess() {
        return get(Type.SUCCESS);
    }
    
    public static Message getWarning() {
        return get(Type.WARNING);
    }
    
    public static Message getInfo() {
        return get(Type.INFO);
    }
    
    public Message content(String content) {
        assertThat(content).isNotNull();
        this.content = content;
        return this;
    }
    
    public Message autoHideMillis(Long autoHideMillis) {
        assertThat(autoHideMillis).isNotNull();
        this.autoHideMillis = autoHideMillis;
        return this;
    }
    
    public Type getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public Long getAutoHideMillis() {
        return autoHideMillis;
    }
}

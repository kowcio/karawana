package karawana.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    final static Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        log.info("session created, set interval");
        event.getSession().setMaxInactiveInterval(10);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("session destroyed");
    }
}
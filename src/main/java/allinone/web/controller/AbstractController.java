package allinone.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {

    private static final Logger   LOGGER                          = LoggerFactory.getLogger(AbstractController.class);

    private static final String   GLOBAL_MESSAGE_ATTR_NAME        = "globalMessages";

    public static final String    ABOUT_TEMPLATE                  = "landingpage/about";
    public static final String    ABOUT_PAGE                      = "/about";

    public static final String    NEWS_TEMPLATE                   = "landingpage/news";
    public static final String    NEWS_PAGE                       = "/news";

    // message timeouts
    public static final Long      MESSAGE_HIDE_TIMEOUT_10         = 10000L;
    public static final Long      MESSAGE_HIDE_TIMEOUT_5          = 5000L;

    // other , common
    protected static final String NO_DATA_COLLECTED_FOR_THIS_NODE = "No data collected for this node.";
//
//    /**
//     * Obsługa wyjątków w aplikacji
//     *
//     * @param request żądanie
//     * @param response odpowiedz
//     * @param ex wyjątek
//     * @return strona na którą zostanie przekierowany użytkownik
//     */
//    @ExceptionHandler(Exception.class)
//    protected String handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
//        LOGGER.error("Unexpected system error: " + ex.getMessage(), ex);
//
//        logout(request, response);
//
//        return redirect(LOGIN_UNEXPECTED_ERROR_PAGE);
//    }
//
//    /**
//     * Wylogowanie użytkownika z systemu
//     *
//     * @param request żądanie
//     * @param response odpowiedz
//     */
//    protected void logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//    }
//
//    /**
//     * Pomocnicza metoda do przekierowań na konkretną stronę.
//     *
//     * @param page strona
//     * @return strona do przekierowania
//     */
//    protected String redirect(String page) {
//        assertThat(page).isNotEmpty();
//        return "redirect:" + page;
//    }
//
//    /**
//     * Pomocnicza metoda dodająca wiadomość do wyświetlenia dla użytkownika.
//     * <p>
//     * Do wykorzystania w przypadku pozostania na tym samym widoku. Jeśli ma
//     * nastąpić redirect na inny widok należy wykorzystać metodę z
//     * RedirectAttributes.
//     *
//     * @param model obiket modelu
//     * @param message wiadomość do dodania
//     */
//    protected void addMessage(Model model, Message message) {
//        assertThat(model).isNotNull();
//        assertThat(message).isNotNull();
//
//        Object o = model.asMap().get(GLOBAL_MESSAGE_ATTR_NAME);
//
//        List<Message> globalMessages = o != null ? (List<Message>) o : new ArrayList<>();
//
//        globalMessages.add(message);
//
//        model.addAttribute(GLOBAL_MESSAGE_ATTR_NAME, globalMessages);
//    }
//
//    /**
//     * Pomocnicza metoda dodająca wiadomość do wyświetlenia dla użytkownika.
//     * <p>
//     * Do wykorzystania w przypadku przekierowania użytkownika na inny widok.
//     *
//     * @param ra obiekt redirectAttributes
//     * @param message wiadomość do dodania
//     */
//    protected void addMessage(RedirectAttributes ra, Message message) {
//        assertThat(ra).isNotNull();
//        assertThat(message).isNotNull();
//
//        Object o = ra.getFlashAttributes().get(GLOBAL_MESSAGE_ATTR_NAME);
//
//        List<Message> globalMessages = o != null ? (List<Message>) o : new ArrayList<>();
//
//        globalMessages.add(message);
//
//        ra.addFlashAttribute(GLOBAL_MESSAGE_ATTR_NAME, globalMessages);
//    }

}

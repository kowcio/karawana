package allinone.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import allinone.web.controller.utils.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    private static final String GLOBAL_MESSAGE_ATTR_NAME = "globalMessages";

    // nazwa atrybutu sesyjego w którym znajduje się obiekt z bocznym menu
    public static final String MENU_ATTR_NAME = "sideMenu";

    public static final String LOGIN_PAGE = "/login";
    public static final String LOGIN_UNEXPECTED_ERROR_PAGE = LOGIN_PAGE + "/error/unexpected";
    public static final String LOGIN_ERROR_PAGE = LOGIN_PAGE + "/error";

    public static final String HOME_TEMPLATE = "landingpage/index";
    public static final String HOME_PAGE = "/home";

    public static final String CONTACT_TEMPLATE = "landingpage/contact";
    public static final String CONTACT_PAGE = "/contact";

    public static final String ABOUT_TEMPLATE = "landingpage/about";
    public static final String ABOUT_PAGE = "/about";

    public static final String NEWS_TEMPLATE = "landingpage/news";
    public static final String NEWS_PAGE = "/news";

    public static final String DASHBOARD_TEMPLATE = "portal/dashboard";
    public static final String DASHBOARD_PAGE = "/dashboard/{deviceGroupId}";

    public static final String DASHBOARD_PAGE_RELOAD = "/dashboard/{deviceGroupId}/reload";

    public static final String NODE_PAGE = "/node/{nodeId}";
    public static final String NODE_TEMPLATE = "portal/device";

    //SETTINGS page
    public static final String SETTINGS_PAGE = "/";
    public static final String SETTINGS_TEMPLATE = "portal/settings";

    public static final String SETTINGS_USERS_PAGE = "/users";
    public static final String SETTINGS_USERS_TEMPLATE = SETTINGS_TEMPLATE + SETTINGS_USERS_PAGE;
    public static final String SETTINGS_USER_ADDORUPDATE_PAGE = "/user/{addOrUpdate}";
    public static final String SETTINGS_USER_ADD_POST_PAGE = "/user/add";
    public static final String SETTINGS_USER_UPDATE_POST_PAGE = "/user/update";
    public static final String SETTINGS_USER_ADD_TEMPLATE = SETTINGS_TEMPLATE + "/create_user";
    public static final String SETTINGS_USER_UPDATE_PAGE = "/user/update/{userId}";
    public static final String SETTINGS_USER_UPDATE_TEMPLATE = SETTINGS_TEMPLATE + "/create_user";
    public static final String SETTINGS_USER_DELETE_PAGE = "/user/delete/{userId}";
    public static final String SETTINGS_USER_DELETE_TEMPLATE = SETTINGS_TEMPLATE + SETTINGS_USERS_PAGE;

    //DEVICES PAGE
    public static final String SETTINGS_DEVICES_PAGE = "/devices";

    public static final String SETTINGS_DEVICES_DETAILS_PAGE = "/devicegroup/details/{deviceGroupId}";
    public static final String SETTINGS_DEVICES_DETAILS_TEMPLATE = SETTINGS_TEMPLATE + "/devices_details";

    public static final String SETTINGS_DEVICES_TEMPLATE = SETTINGS_TEMPLATE + SETTINGS_DEVICES_PAGE;
    public static final String SETTINGS_DEVICES_ADD_PAGE = "/devicegroup/add";

    public static final String SETTINGS_DEVICES_ADD_BOTH_TEMPLATE = SETTINGS_TEMPLATE + "/updateOrAdd_device_group";

    public static final String SETTINGS_DEVICES_ADD_TEMPLATE = SETTINGS_TEMPLATE + "/create_device_group";
    public static final String SETTINGS_DEVICES_UPDATE_PAGE = "/devicegroup/update/{deviceGroupId}";
    public static final String SETTINGS_DEVICES_UPDATE_TEMPLATE = SETTINGS_TEMPLATE + "/update_device_group";
    public static final String SETTINGS_DEVICES_DELETE_PAGE = "/devicegroup/delete/{deviceGroupId}";
    public static final String SETTINGS_DEVICES_DELETE_TEMPLATE = SETTINGS_TEMPLATE + SETTINGS_DEVICES_PAGE;

    public static final String SETTINGS_DEVICES_TURNONALLNOTIFICATIONS_PAGE = "/devicegroup/turnonnotifications/{deviceGroupId}/{turnOnFlag}";

    //AGENTS PAGE
    public static final String SETTINGS_AGENTS_PAGE = "/agents";
    public static final String SETTINGS_AGENTS_TEMPLATE = SETTINGS_TEMPLATE + "/agents";

    public static final String SETTINGS_AGENTS_SAVE_ALERTS_PAGE = "/agents/savealerts/";
    public static final String SETTINGS_AGENTS_DELETE_PLUGINS_PAGE = "/agents/deleteplugins/{agentId}";
    public static final String SETTINGS_AGENTS_DELETE_MONITORING_MANAGEMENT_PAGE = "/agents/deletemm/{agentId}";

    public static final String SETTINGS_NODE_ADD_PAGE = "/node/add";
    public static final String SETTINGS_NODE_ADD_TEMPLATE = SETTINGS_TEMPLATE + "/create_device_group";

    public static final String SLA_TEMPLATE = "portal/sla";
    public static final String SLA_PAGE = "/sla";

    public static final String SLA_DETAILS_TEMPLATE = "portal/sla-details";
    public static final String SLA_DETAILS_PAGE = "/sla/{monitoringNodeId}";

    public static final String CHART_PAGE = "/chart/{chartType}";

    public static final String BCSAADMIN_PAGE = "/bcsaadmin/{clientId}";
    public static final String BCSAADMIN_TEMPLATE = "portal/bcsaadmin";

    //message timeouts
    public static final Long MESSAGE_HIDE_TIMEOUT_10 = 10000L;
    public static final Long MESSAGE_HIDE_TIMEOUT_5 = 5000L;
    public static final String OLD_PASS = "oldPassWhenWeEditTheUser";

    //diskArray controller
    
    protected static final String   USER                           = "User ";
    protected static final String   PORTAL_DISKARR_INFORMATION_TAB = "portal/diskarr/information-tab";
    protected static final String   DISK_LIST_VIEW                 = "diskListView";
    protected static final String   DISK_PORT_VIEW                 = "diskPortView";
    protected static final String   NODE                           = "node";
    protected static final String   CHARTS_TO_RETRIEVE             = "chartsToRetrieve";
    protected static final String   PERIOD_FILTER_FORM             = "periodFilterForm";
    
    //global settings controller
    protected static final String PAGE_AGENTS = "/portal/settings/agents";
    protected static final String PAGE_DEVICES = "/portal/settings/devices";
    protected static final String ADD_PAGE = "addPage";
    protected static final String DEVICE_GROUP = "deviceGroup";
    protected static final String PAGE_USERS = "/portal/settings/users";
    protected static final String PAGE_USER_ADD = "/portal/settings/user/add";
    protected static final String UPDATE = "update";
    protected static final String USER_FORM = "userForm";
    protected static final String ADD = "add";
    protected static final String HELPVAR_ADD_USER = "addUser";
    protected static final String FORM_PRIORITY_COUNT = "priorityCount";
    
    //OSController / OSSettingsController
    protected static final String         OSCMID = "osCmid";
    protected static final String         OSVIEW = "osView";
    protected static final String OS_INFORMATION_TAB = "portal/os/information-tab";
    
    //OS Performance Controller
    protected static final String PORTAL_OS_PERFORMANCE_TAB_DETAILS = "portal/os/performance-tab-details";
    protected static final String  PERFORMANCE_CHARTS = "performanceChartList";

    
    //other , common
    protected static final String NO_DATA_COLLECTED_FOR_THIS_NODE = "No data collected for this node.";
    protected static final String DASHBOARD = "/portal/dashboard/all";

    
    
    /**
     * Obsługa wyjątków w aplikacji
     *
     * @param request żądanie
     * @param response odpowiedz
     * @param ex wyjątek
     * @return strona na którą zostanie przekierowany użytkownik
     */
    @ExceptionHandler(Exception.class)
    protected String handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        LOGGER.error("Unexpected system error: " + ex.getMessage(), ex);

        // wyloguj użytkownika
        logout(request, response);

        return redirect(LOGIN_UNEXPECTED_ERROR_PAGE);
    }

    /**
     * Wylogowanie użytkownika z systemu
     *
     * @param request żądanie
     * @param response odpowiedz
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    /**
     * Pomocnicza metoda do przekierowań na konkretną stronę.
     *
     * @param page strona
     * @return strona do przekierowania
     */
    protected String redirect(String page) {
        assertThat(page).isNotEmpty();
        return "redirect:" + page;
    }

    /**
     * Pomocnicza metoda dodająca wiadomość do wyświetlenia dla użytkownika.
     * <p>
     * Do wykorzystania w przypadku pozostania na tym samym widoku.
     * Jeśli ma nastąpić redirect na inny widok należy wykorzystać metodę z RedirectAttributes.
     *
     * @param model obiket modelu
     * @param message wiadomość do dodania
     */
    protected void addMessage(Model model, Message message) {
        assertThat(model).isNotNull();
        assertThat(message).isNotNull();

        Object o = model.asMap().get(GLOBAL_MESSAGE_ATTR_NAME);

        List<Message> globalMessages = o != null ? (List<Message>) o : new ArrayList<>();

        globalMessages.add(message);

        model.addAttribute(GLOBAL_MESSAGE_ATTR_NAME, globalMessages);
    }

    /**
     * Pomocnicza metoda dodająca wiadomość do wyświetlenia dla użytkownika.
     * <p>
     * Do wykorzystania w przypadku przekierowania użytkownika na inny widok.
     *
     * @param ra obiekt redirectAttributes
     * @param message wiadomość do dodania
     */
    protected void addMessage(RedirectAttributes ra, Message message) {
        assertThat(ra).isNotNull();
        assertThat(message).isNotNull();

        Object o = ra.getFlashAttributes().get(GLOBAL_MESSAGE_ATTR_NAME);

        List<Message> globalMessages = o != null ? (List<Message>) o : new ArrayList<>();

        globalMessages.add(message);

        ra.addFlashAttribute(GLOBAL_MESSAGE_ATTR_NAME, globalMessages);
    }

   
}

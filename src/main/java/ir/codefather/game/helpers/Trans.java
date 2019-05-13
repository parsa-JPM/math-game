package ir.codefather.game.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Trans {

    private static ResourceBundleMessageSource messageSource;


    @Autowired
    public Trans(ResourceBundleMessageSource messageSource) {
        Trans.messageSource = messageSource;
    }


    /**
     * this is shortcut to get messages from translator
     *
     * @param fileBasename name of file that you created
     * @param msgCode      key of message
     * @return String
     */
    public static String get(String fileBasename, String msgCode) {
        setNewBasename(fileBasename);
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(messageSource.getBasenameSet());
        return messageSource.getMessage(msgCode, null, locale);
    }


    /**
     * this give us multiple lang file option
     *
     * @param fileBasename name of file
     */
    private static void setNewBasename(String fileBasename) {
        if (fileBasename != null && !messageSource.getBasenameSet().contains(fileBasename))
            messageSource.addBasenames("lang/" + fileBasename);
    }
}

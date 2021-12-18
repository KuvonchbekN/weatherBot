package bot;

import model.Country;
import model.State;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import service.CountryService;


import java.util.List;

public class ExecuteService implements State {
    static CountryService countryService = new CountryService();
    Markups markups =new Markups();

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


    public SendMessage mainMenuSetting(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(TO_SEE_COUNTRIES);
        return sendMessage;
    }


    public SendMessage showCountries(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(markups.chooseCountryByFirstName(message));
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(TO_SEE_REGIONS);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        return sendMessage;
    }

}

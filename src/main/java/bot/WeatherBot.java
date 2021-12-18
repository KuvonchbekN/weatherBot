package bot;

import model.State;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;


public class WeatherBot extends TelegramLongPollingBot implements State {
    Map<String, String> userActions = new HashMap<String, String>();

    ExecuteService executeService = new ExecuteService();
    Markups markups = new Markups();

    @Override
    public String getBotUsername() {
        return "http://t.me/cloudyWeatherBot";
    }

    @Override
    public String getBotToken() {
        return "5000084509:AAHN2vvCoefDDkr9dzgexZG0p00yMvpd68c";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId;
        if(update.hasMessage()){
            chatId = update.getMessage().getChatId().toString();
            Message message = update.getMessage();

            switch (message.getText()){
                case START -> {
                    userActions.put(message.getChatId().toString(), START);
                    executeMessage(executeService.mainMenuSetting(message));
                }case TO_SEE_COUNTRIES -> {
                    
                }



//                case TO_SEE_COUNTRIES -> ;
//                case TO_SEE_REGIONS -> ;
            }
        }else if (update.hasCallbackQuery()){
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();

        }
    }

    private void executeMessage(SendMessage sendMessage)    { //executing message;
        try {
            execute(sendMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



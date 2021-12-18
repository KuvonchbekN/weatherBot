package bot;

import model.State;
import model.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

import java.util.HashMap;
import java.util.Map;


public class WeatherBot extends TelegramLongPollingBot implements State {
    Markups markups = new Markups();
    UserService userService = new UserService();

    Map<String, String> userActions = new HashMap<String, String>();

    ExecuteService executeService = new ExecuteService();

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
            String userName = message.getFrom().getUserName();
            String firstName = message.getFrom().getFirstName();

                if (!userService.doesExist(chatId)){
                    User user = new User(firstName, userName, chatId, true);
                    userService.add(user);
                }
                if (message.getText().equals(START)) {
                    userActions.put(message.getChatId().toString(), START);
                    executeMessage(executeService.mainMenuSetting(message));
                }else if (message.getText().equals(TO_SEE_COUNTRIES)){
                    executeMessage(executeService.showCountries(message));
                    System.out.println("too see countries");
                    userActions.put(message.getChatId().toString(), TO_SEE_COUNTRIES);
                    markups.backButton(message);
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



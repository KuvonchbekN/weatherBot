package bot;

import model.Country;
import model.State;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.LoginUrl;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.CountryService;

import java.util.ArrayList;
import java.util.List;


public class Markups implements State {
    static CountryService countryService = new CountryService();



    public SendMessage backButton(Message message){
        SendMessage sendMessage = new SendMessage();
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows= new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton kButton = new KeyboardButton();
        kButton.setText(BACK);
        keyboardRow.add(kButton);
        keyboardRows.add(keyboardRow);
        markup.setResizeKeyboard(true);
        markup.setKeyboard(keyboardRows);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode(ParseMode.MARKDOWN);

        return sendMessage;
    }

    public InlineKeyboardMarkup chooseCountryByFirstName(Message message){
        List<Country> countries = countryService.getListByFirstLetter(message.getText()); //all the countries with first letter

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttonsRow = new ArrayList<>();

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        int index = 0;
        for (Country country: countries) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setCallbackData(country.getName());
            button.setText(country.getName());


            buttons.add(button);
                //might not work properly;
    //            LoginUrl loginUrl = new LoginUrl("https://wiut12219.netlify.app/");
    //            button.setLoginUrl(loginUrl);

            if (index==4){
                buttonsRow.add(buttons);
                buttons.clear();
            }
            index++;
        }

        if (buttons.size()!=0){
            buttonsRow.add(buttons);
        }

        markup.setKeyboard(buttonsRow);
        return markup;
    }

}

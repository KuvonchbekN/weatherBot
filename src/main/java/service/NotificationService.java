package service;

import bot.WeatherBot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.naming.ldap.PagedResultsControl;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationService extends WeatherBot {


    private String sms;
    private String chatId;


    public void sendNotification(String chatId, int count, int size){
        SendMessage sendMessage = new SendMessage();
        for (int j=count; (j<count+5 && j<size); j++) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("how you doing");
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

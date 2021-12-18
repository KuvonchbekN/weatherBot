package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DatabasePath;
import model.User;
import response.BaseResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements BaseResponse, DatabasePath {
    static ObjectMapper objectMapper = new ObjectMapper();

    public String add(User user){
        List<User> userList = getUsersList();
        try {
            if (userList  == null){
                userList = new ArrayList<>();
            }
            userList.add(user);
            updateUserDatabase(userList); // need to create this method;
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ERROR;
    }

    public static List<User> getUsersList() {
        List<User> userList = null;

        try {
            userList = objectMapper.readValue(new File(userPath), new TypeReference<>() {
            });
            if(userList == null){
                userList = new ArrayList<>();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    public static void updateUserDatabase(List<User> userList){
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(userPath), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean doesExist(String chatId){
        for (User user1: getUsersList()) {
            if (user1.getChatId().equals(chatId))return true;
        }
        return false;
    }
}

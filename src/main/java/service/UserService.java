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

    public static String add(User user){
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
        List<User> userList = new ArrayList<>();

        try {
            File file = new File(countriesPath);
            if (file.length()!=0){
                List<User> tempList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
                tempList.forEach(user -> {
                    if (user.isActive()) {
                        userList.add(user);
                    }
                });
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void updateUserDatabase(List<User> userList){
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(userPath), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

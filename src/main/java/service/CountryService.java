package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Country;
import model.Data;
import model.DatabasePath;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CountryService implements DatabasePath {
    static ObjectMapper objectMapper = new ObjectMapper();
    List<Country> countries;

     {
         try {
             this.countries = Objects.requireNonNullElseGet(getList(), ArrayList::new);
             System.out.println("getList1");
         } catch (Exception e) {
             System.out.println("Exception!");
             countries = new ArrayList<>();
         }
    }

    public List<Country> getList() { //to get the list of countries in the database
        try {
            System.out.println("1");
            return objectMapper.readValue(new File(countriesPath), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("2");
        return null;
    }

    //writing to dataBase for the first time;
//    {
//        try {
//            URL url = new URL("https://countriesnow.space/api/v0.1/countries");
//            URLConnection urlConnection = url.openConnection();
//            InputStream inputStream = urlConnection.getInputStream();
//
//            Data data = objectMapper.readValue(inputStream, new TypeReference<>() {
//            });
//            countries = data.getCountries();
//            File file = new File("E:\\java lessons\\weatherApp\\src\\main\\java\\countries.json");
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, countries);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public List<String> getRegions(int chosenCountry){ //this is for bot to show all cities(regions) of a chosen country
        int index = 0;

        for (Country country: getList()){
            if (index == chosenCountry-1){
                return country.getRegions();
            }
            index++;
        }
        return null;
    }

    public List<Country> getListByFirstLetter(String fLetter){
        List<Country> countries = new ArrayList<>();
        for (Country country: getList()) {
            if (country.getName().startsWith(fLetter)){
                countries.add(country);
            }
        }
        return countries;
    }



}

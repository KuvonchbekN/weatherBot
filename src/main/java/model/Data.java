package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;




@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @JsonProperty("data")
    private ArrayList<Country> countries;
}


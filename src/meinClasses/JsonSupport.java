package meinClasses;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface JsonSupport {

    Gson gson = new Gson();

    public void fromJson(String s);
    public String toJson();

}

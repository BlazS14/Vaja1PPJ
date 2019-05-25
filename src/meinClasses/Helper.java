package meinClasses;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public final class Helper {

    private Helper(){};

    public static String readFile(String filepath){

        String ret;
        try {
            ret = new Scanner(new File(filepath)).useDelimiter("\\Z").next();
        }catch(IOException i){
            return "File reading error!\n";
        }
        return ret;
    }

    public static void writeFile(String filepath,String data){

        try{

            PrintWriter out = new PrintWriter(filepath);
            out.println(data);
            out.close();

        }catch (IOException i){};
    }
}

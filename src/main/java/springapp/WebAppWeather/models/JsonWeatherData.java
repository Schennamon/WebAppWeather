package springapp.WebAppWeather.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonWeatherData{
    private String city, cloudy;
    private double temperature, feelsLike, windSpeed;

    public JsonWeatherData(String city){
        this.city = city;
        jsonReader(this.city);
    }

    public JsonWeatherData(){}

    public String getCity() {
        return city;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCloudy() {
        return cloudy;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private static String readAll(Reader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public void jsonReader(String city){
        ApplicationContext context = new AnnotationConfigApplicationContext();
        Resource resource = context.getResource("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=f0469d6a28bb744581dad6256612c284&units=metric&lang=ru");

        try{
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String jsonText = readAll(br);
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(jsonText);
                temperature = jsonObject.getJSONObject("main").getDouble("temp");
                feelsLike = jsonObject.getJSONObject("main").getDouble("feels_like");
                windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                cloudy = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getDate(){
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E hh:mm a");

        return formatForDateNow.format(date);
    }
}

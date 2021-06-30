package springapp.WebAppWeather.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import springapp.WebAppWeather.models.JsonWeatherData;

@Controller
public class WeatherController {

    @GetMapping("/city")
    public String weatherGet(@RequestParam String city, Model model){
        JsonWeatherData jd = new JsonWeatherData(city);
        model.addAttribute("name_city", jd.getCity());
        model.addAttribute("temperature", jd.getTemperature());
        model.addAttribute("feels_like", jd.getFeelsLike());
        model.addAttribute("wind_speed", jd.getWindSpeed());
        model.addAttribute("cloudy", jd.getCloudy());
        switch(jd.getCloudy()) {
            case "ясно":
                model.addAttribute("img", "/images/sun.png");
                break;
            case "небольшая облачность":
                model.addAttribute("img", "/images/slightly-cloudy.png");
                break;
            case "дождь":
                model.addAttribute("img", "/images/rain.png");
                break;
            case "небольшой дождь":
                model.addAttribute("img_", "/images/small-rain.png");
                break;
            case "облачно с прояснениями":
                model.addAttribute("img", "/images/clear-at-times.png");
                break;
        }
        return "weather-city";
    }
}

package springapp.WebAppWeather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springapp.WebAppWeather.models.JsonWeatherData;
import springapp.WebAppWeather.models.Post;
import springapp.WebAppWeather.repo.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        JsonWeatherData jd = new JsonWeatherData();
        String[] cities = {"Kharkiv", "Kiev", "Lviv", "Odessa"};

        for(int i = 0; i < cities.length; i++){
            jd.jsonReader(cities[i]);
            model.addAttribute("temperature_"+cities[i], jd.getTemperature());
            model.addAttribute("feels_like_"+cities[i], jd.getFeelsLike());
            model.addAttribute("wind_speed_"+cities[i], jd.getWindSpeed());
            model.addAttribute("cloudy_"+cities[i], jd.getCloudy());
            model.addAttribute("date", jd.getDate());
            switch(jd.getCloudy()) {
                case "ясно":
                    model.addAttribute("img_"+cities[i], "/images/sun.png");
                    break;
                case "небольшая облачность":
                    model.addAttribute("img_"+cities[i], "/images/slightly-cloudy.png");
                    break;
                case "дождь":
                    model.addAttribute("img_"+cities[i], "/images/rain.png");
                    break;
                case "небольшой дождь":
                    model.addAttribute("img_"+cities[i], "/images/small-rain.png");
                    break;
                case "облачно с прояснениями":
                    model.addAttribute("img_"+cities[i], "/images/clear-at-times.png");
                    break;
            }
        }
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        long id = 1;
        if(!postRepository.existsById(id)){
            return "redirect:/home";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "about";
    }
}

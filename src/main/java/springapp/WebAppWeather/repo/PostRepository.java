package springapp.WebAppWeather.repo;

import org.springframework.data.repository.CrudRepository;
import springapp.WebAppWeather.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}

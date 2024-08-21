package pl.pingwit.basic_spring.joke;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/joke")
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }
    @GetMapping
    public List<JokeDto> getRandomJokes(@RequestParam(required = false) Integer count){
        return jokeService.getRandomJokes(count);
    }
}
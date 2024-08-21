package pl.pingwit.basic_spring.joke;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class JokeService {
    private final String url;
    private final RestTemplate restTemplate;

    public JokeService(@Value("${pingwit.joke.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<JokeDto> getRandomJokes(Integer count) {

        if (count == null || count == 0) {
            JokeApiResponse response = restTemplate.getForObject(url, JokeApiResponse.class);
            return List.of(new JokeDto(response.getSetup(), response.getPunchline()));
        } else if (count > 0 && count <= 5) {
            List<JokeDto> result = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                JokeApiResponse response = restTemplate.getForObject(url, JokeApiResponse.class);
                result.add(new JokeDto(response.getSetup(), response.getPunchline()));
            }
            return result;
        } else {
            List<JokeDto> result = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                JokeApiResponse response = restTemplate.getForObject(url, JokeApiResponse.class);
                result.add(new JokeDto(response.getSetup(), response.getPunchline()));
            }
            return result;
        }

    }
}

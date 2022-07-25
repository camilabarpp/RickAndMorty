package com.example.rickandmorty.client;

import com.example.rickandmorty.response.CharacterResponse;
import com.example.rickandmorty.response.EpisodeResponse;
import com.example.rickandmorty.response.ListOfEpisodesResponse;
import com.example.rickandmorty.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = WebClient.builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id) {
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados!")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findLocationById(String id) {
        log.info("Buscando a localização com o id [{}]", id);
        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados!")))
                .bodyToMono(LocationResponse.class);
    }

    public Mono<EpisodeResponse> findEpisodeByID(String id) {
        log.info("Buscando o episódio com o id [{}]", id);
        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados!")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> getAllEpisodes() {
        log.info("Buscando todos episódios!");
        return webClient
                .get()
                .uri("/episode/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados!")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }
}

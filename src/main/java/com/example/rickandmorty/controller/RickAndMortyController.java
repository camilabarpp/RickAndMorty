package com.example.rickandmorty.controller;

import com.example.rickandmorty.client.RickAndMortyClient;
import com.example.rickandmorty.response.CharacterResponse;
import com.example.rickandmorty.response.EpisodeResponse;
import com.example.rickandmorty.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.management.monitor.MonitorNotification;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findAndCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findEpisodeByID(id);
    }
}

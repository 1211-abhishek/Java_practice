package com.example.webclient_practice.service;

import com.example.webclient_practice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Services {

    @Autowired
    private WebClient webClient;

    public Mono<Post> getPostById(int id) {

        Mono<Post> post = webClient.get()
                .uri("/posts/" + id)
                .retrieve()
                .bodyToMono(Post.class);
        System.out.println("Post : " + post);
        return post;
    }

    public Flux<Post> getAllPosts() {

        Flux<Post> post = webClient.get()
                .uri("/posts")
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> Mono.error(new Throwable()))
                .bodyToFlux(Post.class);

        System.out.println("Printing response");
        System.out.println("Post : " + post);
        System.out.println("After post");

        System.out.println(post.subscribe());

        System.out.println("After subscribe");
        return post;

//        webClient.post()
//                .uri("")
//                .retrieve()
//                .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), clientResponse -> Mono.error(new RuntimeException("")))
//                .bodyToMono(String.class);

    }
}

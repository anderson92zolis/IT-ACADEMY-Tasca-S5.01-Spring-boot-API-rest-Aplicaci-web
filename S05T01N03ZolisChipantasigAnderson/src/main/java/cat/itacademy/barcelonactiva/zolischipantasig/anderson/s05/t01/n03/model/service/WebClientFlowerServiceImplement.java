package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.constants.FlowerConstants.*;
// link with an example:     https://howtodoinjava.com/spring-webflux/webclient-get-post-example/
//   youtube vide :         https://www.youtube.com/watch?v=BSRW1HtNyCo&list=LL&index=2&t=1131s

@Service
public class WebClientFlowerServiceImplement implements WebClientFlowerServiceInterface {

    private final WebClient webClient;

    @Autowired
    public WebClientFlowerServiceImplement(WebClient webClient) {
        //Consider defining a bean of type 'org.springframework.web.reactive.function.client.WebClient' in your configuration.
        super();
        this.webClient = webClient;
    }

    @Override
    public Mono<FlowerDTO> createFlower(FlowerDTO flowerDTO) {
        return this.webClient.post()
                .uri(uriBuilder -> uriBuilder.path(CLIENT_FLORS_ADD).build())
                .syncBody(flowerDTO)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }

    @Override
    public Mono<FlowerDTO> updateFlower(int id, FlowerDTO flowerDTO) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder.path(CLIENT_FLORS_UPDATE).build(id))
                .syncBody(flowerDTO)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }

    @Override
    public Mono<String> deleteFlower(int id) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder.path(CLIENT_FLORS_DELETE).build(id))
                .retrieve()
                .bodyToMono(String.class);
    }


    @Override
    public Mono<FlowerDTO> getFlowerDtoById(int id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(CLIENT_FLORS_GET_ONE).build(id))
                .retrieve()
                .bodyToMono(FlowerDTO.class);

    }

    @Override
    public Flux<FlowerDTO> getAllFlowers() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(CLIENT_FLORS_ALL).build())
                .retrieve()
                .bodyToFlux(FlowerDTO.class);
    }




}

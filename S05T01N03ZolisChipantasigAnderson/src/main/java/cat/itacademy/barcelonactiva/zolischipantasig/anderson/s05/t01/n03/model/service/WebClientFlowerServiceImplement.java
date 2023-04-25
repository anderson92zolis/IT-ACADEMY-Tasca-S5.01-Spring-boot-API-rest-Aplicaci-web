package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.constants.FlowerConstants.*;
// link with a example:     https://howtodoinjava.com/spring-webflux/webclient-get-post-example/
//   youtube vide :         https://www.youtube.com/watch?v=BSRW1HtNyCo&list=LL&index=2&t=1131s

@Service
public class WebClientFlowerServiceImplement implements WebClientFlowerServiceInterface {

    private final WebClient webClient;

    @Autowired
    public WebClientFlowerServiceImplement(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Mono<FlowerDTO> createFlower(FlowerDTO flowerDTO) {
        return webClient.post()
                .uri(CLIENT_FLORS_ADD)
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }


    @Override
    public Mono<FlowerDTO> updateFlower(int id, FlowerDTO flowerDTO) {
        return webClient.put()
                .uri(CLIENT_FLORS_UPDATE,id)
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }




    @Override
    public Mono<FlowerDTO> getFlowerDtoById(int id) {
        return webClient.get()
                .uri(CLIENT_FLORS_GET_ONE, id)
                .retrieve()
                /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())*/
                .bodyToMono(FlowerDTO.class);

    }

    @Override
    public Flux<FlowerDTO> getAllFlowers() {
        return webClient.get()
                .uri(CLIENT_FLORS_ALL)
                .retrieve()
                .bodyToFlux(FlowerDTO.class);
    }

    @Override
    public Mono<Void> deleteFlower(int id) {
        return webClient.delete()
                .uri(CLIENT_FLORS_DELETE,id)
                .retrieve()
                .bodyToMono(Void.class);
    }


}

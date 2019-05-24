package monoAndFluxTest;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class MonoAndFluxTest {

    @Test
    public void FluxTest() {
        Flux<String> fluxString = Flux.just("Mojahid", "Zahid", "Jawed")
                .concatWith(Flux.error(new RuntimeException("Exception Occured")))
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.out.println(e));
    }

    @Test
    public void bufferTest() {
        Flux<String> fluxString = Flux.just("Mojahid", "Shahrukh", "Zahid");
        fluxString.buffer()
                .subscribe(System.out::println, (e) -> System.err.println("Error Occured"), () -> System.out.println("Completed"));
    }

    @Test
    public void asTest() {
        Flux<String> fluxString = Flux.just("Mojahid", "Sharukh", "Zahid");
        fluxString.as(Mono::from).subscribe(System.out::println);
    }

    @Test
    public void blockFirstTest() {
        Flux<String> fluxString = Flux.just("Mojahid", "Sharukh", "", "Zahid");
        fluxString.blockFirst()
        ;
        fluxString.subscribe(System.out::println);
    }

    @Test
    public void bufferSizeTest() {
        Flux<String> fluxString = Flux.just("Mojahid", "Sharukh", "", "Zahid");
        fluxString.buffer().subscribe(System.out::println);
        fluxString.buffer(2).subscribe(System.out::println);
    }

    @Test
    public void mapTest() {
        Flux<String> stringFlux = Flux.just("Mojahid", "Zahid", "Shahrukh", "Mojahid");
        stringFlux.map(String::toUpperCase).subscribe(System.out::println);
    }

    @Test
    public void mapSetTest() {
        Flux<String> stringFlux = Flux.just("mojahid", "zahid", "Shahrukh", "Mojahid");
        stringFlux.map(s -> s.concat("Hussain")).subscribe(System.out::println);
        stringFlux.sort().buffer().subscribe(System.out::println);
        stringFlux.flatMap(s->Mono.just(s.toUpperCase()));
        stringFlux.subscribe(System.out::println);
    }

    @Test
    public void stream(){
        String[] array={"Mojahid","Zahid","Shahid"};
        Stream<String> arrayStream=Stream.of(array);
        arrayStream.map(s->s.toUpperCase())
                .forEach(s->System.out.println(s));
    }

    @Test
    public void flatMap(){
        Mono<String> mono1=Mono.just("Mojahid");

        Mono<Integer> mono2=mono1.flatMap(s->{
            return Mono.just(new Integer(s.length()));
        });
        Integer integer=mono2.block();
        System.out.println(integer);
    }

    @Test
    public void mixedTest(){
        Flux<String> words=Flux.just("the","quick","brown","fox","jumped","over","the","lazy","dog");

        List<String> word= Arrays.asList("the","quick","brown","fox","jumped","over","the","lazy","dog");

        Flux<String> myLetters=Flux.fromIterable(word)
                .flatMap(s ->Flux.fromArray(s.split("")))
                .distinct()
                        .sort()
                        .zipWith(Flux.range(1,Integer.MAX_VALUE),
                                (string,count)-> String.format("%2d. %s",count,string)
                                );
        myLetters.log();
                myLetters.subscribe(System.out::println);

    }
}

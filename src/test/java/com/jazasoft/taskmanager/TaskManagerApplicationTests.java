package com.jazasoft.taskmanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerApplicationTests {

	@Test
	public void contextLoads() {
		Flux<String> stringFlux=Flux.just("Mojahid", "Zahid", "Jawed");
		stringFlux.subscribe(System.out::println);

	}

	@Test
	public void MonoTest(){
		Mono.just("Mojahid")
				.map(s->s.concat("Hussain"))
				.subscribe(System.out::println);
	}



}

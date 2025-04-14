package com.example.demo.lombok;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieTest {
	@Test
	public void 영화테스트() {
		Movie movie1 = new Movie();
		movie1.setName("영화1");
		movie1.setDirector("감독1");
		movie1.setDate("개봉일1");
		
		System.out.println(movie1.getName() + " "+ movie1.getDirector()+ " "+ movie1.getDate());
		
		Movie movie2 = new Movie("영화2", "감독2", "개봉일2");
		System.out.println(movie2.toString());
		
		Movie movie3 = Movie.builder()
							.name("영화3")
							.director("감독3")
							.date("개봉일3")
							.build();
		
		System.out.println(movie3.toString());
		
	}
}

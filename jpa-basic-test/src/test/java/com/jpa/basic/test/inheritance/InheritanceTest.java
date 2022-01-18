package com.jpa.basic.test.inheritance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class InheritanceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired MovieRepository movieRepository;
    @Autowired AlbumRepository albumRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("상속관계 매핑 조회 테스트")
    public void findById() throws Exception {

        // given
        Movie movie = Movie.builder()
                .name("바람과 함께 사라지다")
                .actor("ppak")
                .price(10000)
                .director("mrPPak")
                .build();

        Album album = Album.builder()
                .name("명곡 앨범")
                .artist("mrPPak")
                .price(100000)
                .build();

        movieRepository.save(movie);
        albumRepository.save(album);

        // when
        Item item1 = itemRepository.findById(1L).get();
        Item item2 = em.createQuery("select i from Item i where i.id = 1", Item.class).getSingleResult();
        Item item3 = itemRepository.findByIdCustom(1L).get();
    }
}

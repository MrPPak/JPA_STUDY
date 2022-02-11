package jpabook.jpashop;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.service.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemServiceTest {
    /*
        질문 1
        지금 ItemRepository 밖에 없어서 findOne을 하면 returnType이 Item이다.
        근데 나는 Movie를 return 받고 싶다.
        이런 경우 movieRepository를 따로 만들어야 하는가?
     */

    @Autowired
    ItemService itemService;

    @Autowired
    EntityManager em;

    @AfterEach
    void afterEach() {
        itemService.deleteAll();
    }

    @Test
    void save() {
        // when
        Movie movie = 영화_등록("영화1", 10000, 10, "감독1", "배우1");

        // then
        assertThat(movie.getId()).isNotNull();
    }

    @Test
    void findOne() {
        // given
        Movie movie = 영화_등록("영화1", 10000, 10, "감독1", "배우1");

        // when
        // 영속성 컨텍스트가 이미 종료되었으므로 find할 때 movie와는 다른 Movie 객체를 생성
        Item findMovie = itemService.findOne(movie.getId());

        // then
        assertThat(findMovie.getId()).isEqualTo(movie.getId());
        assertThat(findMovie).isNotEqualTo(movie);
    }

    @Test
    @Transactional
    void findOneWithTransaction() {
        // given
        Movie movie = 영화_등록("영화1", 10000, 10, "감독1", "배우1");

        // when
        // @Transactional이 붙어있으므로 트랜잭션 유지 -> 영속성 컨텍스트 유지됨
        // 영속성 컨텍스트가 유지되므로 findOne을 하면 영속성컨텍스트에 있는 movie 객체를 가져옴
        Item findMovie = itemService.findOne(movie.getId());

        // then
        assertThat(findMovie.getId()).isEqualTo(movie.getId());
        assertThat(findMovie).isEqualTo(movie);
    }

    @Test
    @Transactional
    void findOneWhenEntityMangerClear() {
        // given
        Movie movie = 영화_등록("영화1", 10000, 10, "감독1", "배우1");
        em.flush();
        em.clear();

        // when
        // em.clear()로 영속성컨텍스트의 모든 정보가 사라짐
        // 따라서 findOne 객체는 영속화된 movie 객체와는 다른 객체 생성
        Item findMovie = itemService.findOne(movie.getId());

        // then
        assertThat(findMovie.getId()).isEqualTo(movie.getId());
        assertThat(findMovie).isNotEqualTo(movie);
    }

    @Test
    void findAll() {
        // given
        Movie movie = 영화_등록("영화1", 10000, 10, "감독1", "배우1");
        Movie movie2 = 영화_등록("영화2", 20000, 20, "감독2", "배우2");

        // when
        List<Item> items = itemService.findItems();

        // then
        assertThat(items).hasSize(2);
    }

    private Movie 영화_등록(String name, int price, int stockQuantity, String director, String actor) {
        Movie movie = new Movie(name, price, stockQuantity, director, actor);

        // 트랜잭션 시작
        itemService.saveItem(movie); // ItemService 내에서 영속화되어 movie는 Id를 할당받음
        // 트랜잭션 끝 -> 영속성 컨텍스트 종료

        return movie;
    }

}

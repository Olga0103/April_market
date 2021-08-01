package ru.gb.april.market.april_market;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.april.market.april_market.models.Category;
import ru.gb.april.market.april_market.repositories.CategoryRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void categoryRepositoryTest() {
        Category cat = new Category();
        cat.setTitle("Fiction");
        entityManager.persist(cat);
        entityManager.flush();

        List<Category> catList = categoryRepository.findAll();

        Assertions.assertEquals(2, catList.size());
        Assertions.assertEquals("Fiction", catList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Category> genresList = categoryRepository.findAll();
        Assertions.assertEquals(1, genresList.size());
    }



}

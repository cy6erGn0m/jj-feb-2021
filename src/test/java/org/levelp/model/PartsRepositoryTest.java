package org.levelp.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.levelp.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@SpringBootTest
public class PartsRepositoryTest {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PartsRepository partsRepository;

    @Before
    public void configure() {
        Storage newStorage = new Storage("test-storage");
        Part newPart = new Part("111", "My part");
        newPart.setStorage(newStorage);

        manager.persist(newStorage);
        partsRepository.save(newPart);
    }

    @Test
    public void findByStorageTitle() {
        assertTrue(partsRepository.findByStorageTitle("test-storage-11").isEmpty());
        assertEquals("My part", partsRepository.findByStorageTitle("test-storage").get(0).getTitle());
    }

    @Test
    public void findByStorageTitleWithPaging() {
        Page<Part> page = partsRepository.findByStorageTitlePaging("test-storage",
                PageRequest.of(1, 100)
        );

        PageRequest.of(1, 100, Sort.Direction.ASC, "title");
        PageRequest.of(1, 100,
                Sort.by(Sort.Direction.ASC, "title").
                        and(Sort.by(Sort.Direction.DESC, "partNumber"))
        );

        assertEquals(1, page.getTotalPages());
        assertEquals(1, page.getTotalElements());
        Part singlePart = page.stream().findFirst().get();

        assertEquals("My part", singlePart.getTitle());
    }

//    @Test
//    public void findAllSortedBy() {
//        assertEquals("My part", partsDAO.findAllSortedBy("title").get(0).getTitle());
//
//        boolean failed = false;
//        try {
//            partsDAO.findAllSortedBy("non-existing-column");
//        } catch (Throwable expected) {
//            failed = true;
//        }
//        if (!failed) {
//            fail("Shouldn't pass here");
//        }
//    }

    @Test
    public void saveNewPart() {
        Part added = partsRepository.saveNewPart("part-1", "First part");

        manager.refresh(added);
    }
}

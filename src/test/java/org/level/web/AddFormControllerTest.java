package org.level.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.levelp.model.Part;
import org.levelp.model.PartsRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.mockito.ArgumentMatchers.matches;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class AddFormControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartsRepository parts;

    @MockBean
    private EntityTransaction tx;

    @Autowired
    private EntityManager manager;

    @Test
    public void testPostAdd() throws Exception {
        Part added = new Part("part-111", "Part 1");
        Mockito.when(manager.getTransaction()).thenReturn(tx);
        Mockito.when(parts.saveNewPart(matches("part-111"), matches("Part 1")))
                .thenReturn(added);

        UserSession userSession = new UserSession();
        userSession.setUserLogin("admin");
        userSession.setAdmin(true);

        mvc.perform(post("/add")
                .param("partNumber", "part-111")
                .param("partTitle", "Part 1")
                .sessionAttr("user-session", userSession)
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("itemName", "Part 1"));

        Mockito.verify(parts, Mockito.atLeast(1))
                .saveNewPart("part-111", "Part 1");
    }
}

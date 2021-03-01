package org.level.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.levelp.model.Part;
import org.levelp.model.PartsDAO;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.levelp.TestConfiguration;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class StartPageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartsDAO parts;

    @Test
    public void testNoParts() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Hello, MVC!"))
                .andExpect(model().attribute("parts", Collections.emptyList()));
    }

    @Test
    public void testHaveSomeParts() throws Exception {
        List<Part> expectedParts = Arrays.asList(
                new Part("part1", "Some part")
        );
        Mockito.when(parts.findAll()).thenReturn(expectedParts);

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("title", "Hello, MVC!"))
                .andExpect(model().attribute("parts", expectedParts));
    }
}

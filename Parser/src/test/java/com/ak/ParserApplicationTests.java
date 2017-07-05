package com.ak;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ParserApplicationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void invalidData() {
        try {
            MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(fileUpload("/upload"))
                    .andExpect(status().is(200))
                    .andExpect(content().string("You didn't choose any files"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validData(){
        try {
            final File file = new ClassPathResource("testfile.txt").getFile();
            MockMultipartFile first = new MockMultipartFile("files","testfile.txt", "text/plain", new FileInputStream(file));
            mockMvc.perform(fileUpload("/upload").file(first))
                    .andExpect(status().is(200))
                    .andExpect(content().string("All files uploaded successfully"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getData(){
        try {
            final File file = new ClassPathResource("testfile.txt").getFile();
            MockMultipartFile first = new MockMultipartFile("files","testfile.txt", "text/plain", new FileInputStream(file));
            mockMvc.perform(fileUpload("/upload").file(first));
            mockMvc.perform(get("/parse")).andExpect(content().string("{\"test, line\":1,\"test\":3,\"line line\":1}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

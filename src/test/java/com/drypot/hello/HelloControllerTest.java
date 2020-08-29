/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.drypot.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("hello"));
    }

    @Test
    public void helloParam() throws Exception {
        this.mockMvc.perform(get("/hello-param").param("name", "aaa"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("hello"))
            .andExpect(jsonPath("$.value").value("aaa"));
    }

    @Test
    public void helloParam2() throws Exception {
        this.mockMvc.perform(get("/hello-param")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("hello"))
            .andExpect(jsonPath("$.value").value(""));
    }

    @Test
    public void helloPath() throws Exception {
        this.mockMvc.perform(get("/hello-path/bbb"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("hello"))
            .andExpect(jsonPath("$.value").value("bbb"));
    }

    @Test
    public void helloPath2() throws Exception {
        this.mockMvc.perform(get("/hello-path"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("hello"))
            .andExpect(jsonPath("$.value").value(""));
    }

}

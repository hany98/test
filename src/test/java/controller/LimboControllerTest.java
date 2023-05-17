package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanzy.stake.limbo.hanzimbo.requests.GenerateRequest;
import hanzy.stake.limbo.hanzimbo.service.highlevel.impl.LimboServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static hanzy.stake.limbo.hanzimbo.constants.Endpoints.LIMBO_ENDPOINT;
import static hanzy.stake.limbo.hanzimbo.constants.Endpoints.GENERATE_ENDPOINT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LimboControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LimboServiceImpl limboServiceImpl;

    @Test
    public void computeLimboSuccessTest() throws Exception {
        GenerateRequest generateRequest = new GenerateRequest()
                .setClientSeed("Iyal5gneLc")
                .setServerSeed("827435a31f1d739df9d0a42d62290f499efb6975dcc76d97469b193cb81733e0")
                .setGameId("LIMBO")
                .setStartNonce(0)
                .setEndNonce(600);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(LIMBO_ENDPOINT + GENERATE_ENDPOINT)
                        .content(mapper.writeValueAsString(generateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk());
    }
}

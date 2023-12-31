package com.example.kakao.cart;

import com.example.kakao._core.errors.GlobalExceptionHandler;
import com.example.kakao._core.security.JWTProvider;
import com.example.kakao._core.security.SecurityConfig;
import com.example.kakao._core.util.DummyEntity;
import com.example.kakao._core.utils.FakeStore;
import com.example.kakao.log.ErrorLogJPARepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@Import({
        SecurityConfig.class,
        GlobalExceptionHandler.class,
        FakeStore.class
})
@WebMvcTest(controllers = {CartRestController.class})
public class CartRestControllerTest extends DummyEntity {

    @MockBean
    private ErrorLogJPARepository errorLogJPARepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @WithMockUser(username = "ssar@nate.com", roles = "USER")
    @Test
    public void cart_save_test() throws Exception{
        //given
        List<CartRequest.SaveDTO> requestDTOs = new ArrayList<>();

        CartRequest.SaveDTO d1 = new CartRequest.SaveDTO();
        d1.setQuantity(1);
        d1.setOptionId(5);
        requestDTOs.add(d1);

        CartRequest.SaveDTO d2 = new CartRequest.SaveDTO();
        d2.setQuantity(2);
        d2.setOptionId(5);
        requestDTOs.add(d2);

        String requestBody = om.writeValueAsString(requestDTOs);
        System.out.println("테스트 : "+requestBody);

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carts/add")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"));
    }

    @WithMockUser(username = "ssar@nate.com", roles = "USER")
    @Test
    public void cart_save_test_failure() throws Exception{
        //given
        List<CartRequest.SaveDTO> requestDTOs = new ArrayList<>();
        CartRequest.SaveDTO d1 = new CartRequest.SaveDTO();
        d1.setQuantity(-1); // 유효하지 않은 수량으로 설정
        d1.setOptionId(5);
        requestDTOs.add(d1);

        CartRequest.SaveDTO d2 = new CartRequest.SaveDTO();
        d2.setQuantity(2);
        d2.setOptionId(-1); // 유효하지 않은 옵션 ID로 설정
        requestDTOs.add(d2);

        String requestBody = om.writeValueAsString(requestDTOs);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carts/add")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value("false"));
    }

    @WithMockUser(username = "ssar@nate.com", roles = "USER")
    @Test
    public void cart_find_test() throws Exception{
        // given

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .get("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"));
    }

    @Test
    public void findCart_test_failure_unauthenticated() throws Exception {
        // given

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .get("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized()); // 로그인이 안된 사용자는 조회를 할 수 없음
    }

    @WithMockUser(username = "ssar@nate.com", roles = "USER")
    @Test
    public void update_test() throws Exception {
        // given
        List<CartRequest.UpdateDTO> requestDTOs = new ArrayList<>();
        CartRequest.UpdateDTO d1 = new CartRequest.UpdateDTO();
        d1.setCartId(1);
        d1.setQuantity(10);
        CartRequest.UpdateDTO d2 = new CartRequest.UpdateDTO();
        d2.setCartId(2);
        d2.setQuantity(10);
        requestDTOs.add(d1);
        requestDTOs.add(d2);
        String requestBody = om.writeValueAsString(requestDTOs);
        System.out.println("테스트 : "+requestBody);

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carts/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.response.carts[0].cartId").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.response.carts[0].optionId").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.response.carts[0].optionName").value("01. 슬라이딩 지퍼백 크리스마스에디션 4종"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.response.carts[0].quantity").value(10));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.response.carts[0].price").value(100000));
    }

    @WithMockUser(username = "ssar@nate.com", roles = "USER")
    @Test
    public void update_test_failure() throws Exception {
        // given
        List<CartRequest.UpdateDTO> requestDTOs = new ArrayList<>();
        CartRequest.UpdateDTO d1 = new CartRequest.UpdateDTO();
        d1.setCartId(-1);
        d1.setQuantity(10);
        CartRequest.UpdateDTO d2 = new CartRequest.UpdateDTO();
        d2.setCartId(2);
        d2.setQuantity(-10);
        requestDTOs.add(d1);
        requestDTOs.add(d2);
        String requestBody = om.writeValueAsString(requestDTOs);
        System.out.println("테스트 : "+requestBody);

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carts/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then

    }

    @WithMockUser(username = "ssar@nate.com", roles = "USER")
    @Test
    public void clearCart_test() throws Exception{
        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carts/clear")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+ responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.success").value("true"));
    }

    @Test
    public void clearCart_test_failure_unauthenticated() throws Exception {
        // given

        // when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carts/clear")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        String responseBody = result.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized()); // 로그인이 안된 사용자는 비울 수 없음
    }
}
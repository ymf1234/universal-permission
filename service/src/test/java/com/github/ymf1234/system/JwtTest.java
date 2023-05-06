package com.github.ymf1234.system;

import com.github.ymf1234.common.result.Jwt;
import com.github.ymf1234.common.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class JwtTest {
    @Test
    public void jwt() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Jwt.build().getUserId(), 1);
        map.put(Jwt.build().getUsername(), "2");
        String token = JwtHelper.createToken(map);
        System.out.println(token);
    }

    @Test
    public void jwtParesJWT() {
        String token = "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJS8goPUdJRSq0oULIyNDc0sbCwNDQx1VEqLU4t8kwBikGYeYm5qUDFRkq1AIAV5uw4AAAA.Mh9JI_mxgTd24-mSHxR3M37B9ElWLyjbREJNc0MCX7rYykVWYjt1kpp-GITm4JFNWsWt1n-LyxRxcqS0X9bAPA";
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUsername(token));
    }

}

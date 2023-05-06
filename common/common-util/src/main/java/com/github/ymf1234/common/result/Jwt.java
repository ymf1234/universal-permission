package com.github.ymf1234.common.result;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
public class Jwt {
    private String userId = "userId";

    private String username = "username";

    private String token = "token";

    public static Jwt build() {
        return new Jwt();
    }
}

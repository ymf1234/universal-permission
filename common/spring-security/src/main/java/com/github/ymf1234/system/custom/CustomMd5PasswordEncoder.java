package com.github.ymf1234.system.custom;

import com.github.ymf1234.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码处理
 */
public class CustomMd5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5.encrypt(charSequence.toString()));
    }
}

package com.hwarim.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        //
        // -> 앤드 포인트 접근 불가. 1. 사용자가 없다, passwordEncoder가 없다.

        /**
         * 추가해줘야할 거
         *  1. 자격 증명(사용자 이름 및 암호)이 있는 사용자를 하나 이상 만든다.
         *  2. 사용자를 UserDetailsService에서 관리하도록 추가한다.
         *  3. UserDetailsService가 저장하고 관리하는 암호를 이용해 검증하는 PasswordEncoder 형식의 빈을 정의한다.
         */

        // 1.
        var user = User.withUsername("hwarim")
                .password("12345")
                .authorities("read")
                .build();

        // 2.
        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // UserDetailsService를 재정의 한다면 passwordEncoder도 같이 재정의 해줘야함.
        // 안그러면 java.lang.IllegalArgumentException There is no PasswordEncoder mapped for the id "null" e 에러남.
        return NoOpPasswordEncoder.getInstance();
    }
}

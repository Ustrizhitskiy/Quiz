package com.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    // этим ресурсом (корневым) разрешаем пользоваться всем:
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .antMatchers("/", "/registration", "/login", "/static/**").permitAll()
                    // а всеми остальными ресурсами - только авторизованным пользователем:
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
        http.csrf().disable();  // Без этой строчки все равно не будет доступа к ресурсам
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource) // Нужен, чтобы менеджер мог ходить в БД и искать там пользователей и их роли. Для этого заавтовайрить DataSource sql
                .passwordEncoder(NoOpPasswordEncoder.getInstance())     // шифрует пароли
                // далее запрашиваем поля login, password, active, чтобы найти пользователя по имени (обязательно в таком порядке):
                .usersByUsernameQuery("select username, password, active from users where username=?")
                // из таблицы "usr" и присоединенной к ней через поля "id" и "user_id" таблице "user_role" выбираем поля "login" и роли:
                .authoritiesByUsernameQuery("select u.username, ur.roles from users u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }

}

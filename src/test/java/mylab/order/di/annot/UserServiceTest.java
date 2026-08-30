package mylab.order.di.annot;

import mylab.user.di.annot.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml") // applicationContext = new GenericXmlApplicationContext("classpath:mylab-user-di.xml");
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void userServiceInjectionTest() {
        // 1. UserService 레퍼런스가 Not Null 인지 검증
        assertNotNull(userService);

        // 2. userService.getUserRepository() 가 Not Null 인지 검증
        assertNotNull(userService.getUserRepository());

        // 3. userService.getUserRepository().getDbType() 값이 MySQL 인지 비교
        assertEquals("MySQL", userService.getUserRepository().getDbType(), "dbType 값 불일치");

        // 4. userService.getSecurityService() 가 Not Null 인지 검증
        assertNotNull(userService.getSecurityService());
    }

    @Test
    void registerUserTest() {
        // password가 전달되면 인증 성공 -> True
        assertTrue(userService.registerUser("user01", "홍길동", "pw1234"));

        // password가 전달되지 않으면(null) 인증 실패 -> False
        assertFalse(userService.registerUser("user02", "김철수", null));

        // password가 빈 문자열이어도 인증 실패 -> False
        assertFalse(userService.registerUser("user03", "이영희", ""));
    }
}

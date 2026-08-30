package mylab.order.di.annot.config;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationConfig;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class, loader = AnnotationConfigContextLoader.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void notificationManagerInjectionTest() {
        // a. NotificationManager 레퍼런스가 Not Null 인지 검증
        assertNotNull(notificationManager);

        // b. 이메일 서비스 검증
        assertNotNull(notificationManager.getEmailService());
        EmailNotificationService emailService =
                (EmailNotificationService) notificationManager.getEmailService();
        assertEquals("smtp.gmail.com", emailService.getSmtpServer(), "SMTP 서버 불일치");
        assertEquals(587, emailService.getPort(), "포트 번호 불일치");

        // d. SMS 서비스 검증
        assertNotNull(notificationManager.getSmsService());
        SmsNotificationService smsService =
                (SmsNotificationService) notificationManager.getSmsService();
        assertEquals("SKT", smsService.getProvider(), "제공업체 불일치");
    }

    @Test
    void notificationManagerMethodTest() {
        // e. NotificationManager의 메서드 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}

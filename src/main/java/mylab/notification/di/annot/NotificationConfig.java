package mylab.notification.di.annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // "이 클래스가 Bean 정의를 담은 설정 파일이다" 라는 선언
public class NotificationConfig {

    // 이메일 알림 서비스: SMTP 서버, 포트 설정
    @Bean
    public EmailNotificationService emailNotificationService() {
        return new EmailNotificationService("smtp.gmail.com", 587);
    }

    // SMS 알림 서비스: 제공업체 설정
    @Bean
    public SmsNotificationService smsNotificationService() {
        return new SmsNotificationService("SKT");
    }

    // 알림 매니저: 이메일 / SMS 서비스 주입
    @Bean
    public NotificationManager notificationManager() {
        return new NotificationManager(emailNotificationService(), smsNotificationService());
    }
}

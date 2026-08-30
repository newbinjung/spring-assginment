package mylab.order.di.xml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderSpringTest {
    ApplicationContext applicationContext;

    private ShoppingCart shoppingCart;
    private OrderService orderService;

    @BeforeEach
    void setup() {
        System.out.println("==> setup");
        // 1. Spring Container 객체생성
        applicationContext = new GenericXmlApplicationContext("classpath:mylab-order-di.xml");

        // 2. ShoppingCart와 OrderService 주입 받기
        shoppingCart = applicationContext.getBean("shoppingCart", ShoppingCart.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    void startShoppingCartTest() {
        System.out.println("테스트 시작");
        // 1. shoppingCart 객체가 Null이 아닌지 검증
        assertNotNull(shoppingCart);
        assertNotNull(orderService);

        // 2. shoppingCart.getProducts.size() 검증
        assertEquals(2, shoppingCart.getProducts().size(), "상품 개수가 올바르지 않습니다.");

        // 3. shoppingCart.getProducts.get(0).getName()이 노트북인지 검증
        assertEquals("노트북", shoppingCart.getProducts().get(0).getName(), "첫번째 상품 불일치");
        // 4. shoppingCart.getProducts.get(0).getName()이 스마트폰인지 검증
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName(), "두번째 상품 불일치");

    }

    @Test
    void startOrderServiceTest() {
        // 1. orderService 객체가 Null 이 아닌지 검증하세요. ( assertNotNull() )
        assertNotNull(orderService);
        // 2. orderService.getShoppingCart() 객체가 Null 이 아닌지 검증하세요. ( assertNotNull() )
        assertNotNull(orderService.getShoppingCart());
        // 3. orderService.calculateOrderTotal() 메서드의 호출결과 값을 검증하세요 ( assertEquals() )
        assertEquals(815000.0, orderService.calculateOrderTotal(), "상품 개수가 올바르지 않습니다.");
    }

}

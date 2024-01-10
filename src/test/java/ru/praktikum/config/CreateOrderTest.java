package ru.praktikum.config;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.client.OrderClient;
import ru.praktikum.step.OrderSteps;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderSteps orderSteps;
    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][]data() {
        return new Object[][] {
                new String[][]{{"BLACK"}},
                new String[][]{{"GREY"}},
                new String[][]{{"BLACK", "GREY"}},
                new String[][]{{}},
        };
    }
    @Before
    public void setUp() {
        orderSteps = new OrderSteps(new OrderClient());
    }
    @Test
    public void CreateOrderColor() {
        String firstName = RandomStringUtils.randomAlphabetic(10);
        String lastName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphabetic(10);
        String metroStation = RandomStringUtils.randomAlphabetic(10);
        String phone = RandomStringUtils.randomNumeric(11);
        int rentTime = 1 + (int) (Math.random() * 168);
        String deliveryDate = "2023-12-29";
        String comment = RandomStringUtils.randomAlphabetic(20);

        orderSteps
                .createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color)
                .log().all()
                .statusCode(201)
                .body("track", notNullValue());
    }
}
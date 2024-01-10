package ru.praktikum.config;


import org.junit.Before;
import org.junit.Test;
import ru.praktikum.client.OrderClient;
import ru.praktikum.step.OrderSteps;
import static org.hamcrest.Matchers.notNullValue;

public class GetAllListOrder {
    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        orderSteps = new OrderSteps(new OrderClient());
    }

    @Test
    public void getAllListOrders() {
        Integer courierId = null;
        String nearestStation = "some";
        Integer limit = 30;
        Integer page = 0;

        orderSteps
                .getAllListOrders(courierId, nearestStation, limit, page)
                .statusCode(200)
                .body("orders", notNullValue());
    }
}

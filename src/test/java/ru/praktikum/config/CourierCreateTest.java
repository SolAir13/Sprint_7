package ru.praktikum.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.client.CourierClient;
import ru.praktikum.step.CourierSteps;
import static org.hamcrest.Matchers.is;

public class CourierCreateTest {
    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }

    @Test
    public void createCourierSuccessfully() {
        String login = RandomStringUtils.random(10);
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName)
                .statusCode(201)
                .body("ok", is(true));

        Integer photoId = courierSteps
                .login(login, password)
                .extract().body().path("id");
        courierSteps
                .delete(photoId);
    }

    @Test
    public void createCourierWhithOutOneSring() {
        String login = "";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    public void cantCreateTwoIdenticalCouriers() {
        String login = "AlfradoCourier";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName);
        courierSteps
                .create(login, password, firstName)
                .statusCode(409)
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }
}


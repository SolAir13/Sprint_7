package ru.praktikum.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.client.CourierClient;
import ru.praktikum.dto.CourierCreateRequest;
import ru.praktikum.step.CourierSteps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class LoginCourierTest {
    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }

    @Test
    public void loginCourier() {

        String login = RandomStringUtils.random(10);
        String password = RandomStringUtils.random(10);
        String firsName = RandomStringUtils.random(10);

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword("1234");
        courierCreateRequest.setFirstName("Al");

        courierSteps.create(login, password, firsName);

        courierSteps.login(login, password)
                .statusCode(200)
                .body("id", notNullValue());
    }
    @Test
    public void loginCourierWithoutLogin() {
        String login = RandomStringUtils.random(10);
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName);

        courierSteps
                .login("", password)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }
    @Test
    public void loginCourierWithoutPassword() {
        String login = RandomStringUtils.random(10);
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName);

        courierSteps
                .login(login, "")
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }
    @Test
    public void loginCourierWithWrongLogin() {
        String login = "AlexTest";
        String password = RandomStringUtils.random(10);
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName);

        courierSteps
                .login("AlexTestLogin", password)
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }
    @Test
    public void loginCourierWithWrongPassword() {
        String login = RandomStringUtils.random(10);
        String password = "AlexTestPassword";
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .create(login, password, firstName);

        courierSteps
                .login(login, "AlexTestPassword1")
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }
}
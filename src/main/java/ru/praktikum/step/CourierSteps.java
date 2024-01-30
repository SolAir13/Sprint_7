package ru.praktikum.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.client.CourierClient;
import ru.praktikum.dto.CourierCreateRequest;
import ru.praktikum.dto.CourierDeleteRequest;
import ru.praktikum.dto.CourierLoginRequest;

public class CourierSteps {
    private final CourierClient courierClient;

    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }
    @Step("Send request body and get response")
    public ValidatableResponse create(String login, String password, String firstName) {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);
        return courierClient.create(courierCreateRequest)
                .then();
    }
    @Step("Send request body and get response")
    public ValidatableResponse login (String login, String password){
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        return courierClient.login(courierLoginRequest)
                .then();
    }
    @Step("Delete id")
    public ValidatableResponse delete(Integer id) {
        CourierDeleteRequest courierDeleteRequest = new CourierDeleteRequest();
        courierDeleteRequest.setId(id);
        return courierClient.deleteCourier(courierDeleteRequest)
                .then();
    }
}

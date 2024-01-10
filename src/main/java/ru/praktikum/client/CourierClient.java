package ru.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.praktikum.dto.CourierCreateRequest;
import ru.praktikum.dto.CourierDeleteRequest;
import ru.praktikum.dto.CourierLoginRequest;

import static io.restassured.RestAssured.given;
import static ru.praktikum.config.Config.BASE_URI;

public class CourierClient extends RestClient{
    @Step("Send POST request to /api/v1/courier")
    public Response create(CourierCreateRequest courierCreateRequest){
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(courierCreateRequest)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response login(CourierLoginRequest courierLoginRequest){
        return getDefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Send POST request to /api/v1/courier/:id")
    public Response deleteCourier(CourierDeleteRequest courierLoginRequest) {
        return getDefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/api/v1/courier/:id");
    }
}

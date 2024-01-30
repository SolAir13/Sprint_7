package ru.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.praktikum.config.Config.BASE_URI;

public class RestClient {
    @Step("Send default URL https://qa-scooter.praktikum-services.ru/api/v1/")
    protected RequestSpecification getDefaultRequestSpecification(){
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}

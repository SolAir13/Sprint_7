package ru.praktikum.client;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.dto.GetAListOfOrdersRequest;
import ru.praktikum.dto.OrderCreateRequest;

public class OrderClient extends RestClient {

   @Step("Send POST request to /api/v1/orders")
    public Response createOrder(OrderCreateRequest orderCreateRequest) {
        return getDefaultRequestSpecification()
                .body(orderCreateRequest)
                .when()
                .post("api/v1/orders");
    }

    @Step("Send GET request to /api/v1/orders")
    public Response getAllListOrders(GetAListOfOrdersRequest getAListOfOrdersRequest) {
        return getDefaultRequestSpecification()
                .log().all()
                .body(getAListOfOrdersRequest)
                .when()
                .get("/api/v1/orders");
    }

}

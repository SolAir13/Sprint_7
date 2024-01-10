package ru.praktikum.step;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.client.OrderClient;
import ru.praktikum.dto.GetAListOfOrdersRequest;
import ru.praktikum.dto.OrderCreateRequest;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    private final OrderClient orderClient;

    public OrderSteps(OrderClient orderClient) {
        this.orderClient = orderClient;
    }
    @Step("Send request body and get response")
    public ValidatableResponse createOrder(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setFirstName(firstName);
        orderCreateRequest.setLastName(lastName);
        orderCreateRequest.setAddress(address);
        orderCreateRequest.setMetroStation(metroStation);
        orderCreateRequest.setPhone(phone);
        orderCreateRequest.setRentTime(rentTime);
        orderCreateRequest.setDeliveryDate(deliveryDate);
        orderCreateRequest.setComment(comment);
        orderCreateRequest.setColor(color);
        return orderClient.createOrder(orderCreateRequest).then();
    }
    @Step("Send request body and get response")
    public ValidatableResponse getAllListOrders(Integer courierId, String nearestStation, Integer limit, Integer page) {
        GetAListOfOrdersRequest getAListOfOrdersRequest = new GetAListOfOrdersRequest();
        getAListOfOrdersRequest.setCourierId(courierId);
        getAListOfOrdersRequest.setNearestStation(nearestStation);
        getAListOfOrdersRequest.setLimit(limit);
        getAListOfOrdersRequest.setPage(page);
        return orderClient.getAllListOrders(getAListOfOrdersRequest).then();
    }

}

package org.lab.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import org.lab.models.Reservation;
import org.lab.services.ReservationServices;

import java.util.HashMap;
import java.util.Map;

public class ReservationApi {
    private final ReservationServices reservationServices = new ReservationServices();
    private final Gson gson = new Gson();

    public APIGatewayProxyResponseEvent handlerRequestApi(APIGatewayProxyRequestEvent request, Context context){
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        String requestMethod = request.getHttpMethod();
        context.getLogger().log("Http Method"+request.getHttpMethod());
        context.getLogger().log(request.getBody());
        context.getLogger().log(gson.fromJson(request.getBody(), Reservation.class).getName());
        Reservation reservation = gson.fromJson(request.getBody(), Reservation.class);
        response.setBody(reservationServices.insertReservation(reservation, context));
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);
        return response;
    }

    public APIGatewayProxyResponseEvent handleRequestGet(APIGatewayProxyRequestEvent request, Context context){
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        context.getLogger().log("Http Method"+request.getHttpMethod());
        context.getLogger().log(request.getBody());
        response.setBody(reservationServices.getReservations(context));
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);
        return response;
    }

//    public APIGatewayProxyResponseEvent handleRequestDelete(APIGatewayProxyRequestEvent request, Context context){
//        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
//        context.getLogger().log("Http Method"+request.getHttpMethod());
//        context.getLogger().log(request.getBody());
//
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Access-Control-Allow-Origin", "*");
//        response.setHeaders(headers);
//
//
//    }
}

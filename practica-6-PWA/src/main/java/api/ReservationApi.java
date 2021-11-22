package api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import models.Reservation;
import services.ReservationServices;

import java.rmi.server.RemoteServer;
import java.util.HashMap;
import java.util.Map;

public class ReservationApi {
    private final ReservationServices reservationServices = new ReservationServices();
    private final Gson gson = new Gson();

    public APIGatewayProxyResponseEvent handlerRequestApi(APIGatewayProxyRequestEvent request, Context context){
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        String requestMethod = request.getHttpMethod();
        context.getLogger().log("Http Method"+request.getHttpMethod());

        switch (requestMethod){

            case "GET":
                response.setBody("This is the Get request");
                break;
            case "POST":
                context.getLogger().log(request.getBody());
                Reservation reservation = gson.fromJson(request.getBody(), Reservation.class);
                response.setBody(reservationServices.insertReservation(reservation, context));
                break;
            case "DELETE":
                response.setBody("This is the delete");
                break;
        }
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);
        return response;
    }
}

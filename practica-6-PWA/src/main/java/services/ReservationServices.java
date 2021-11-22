package services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import models.Reservation;

public class ReservationServices {


    public String insertReservation(Reservation reservation, Context context){
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

        if (reservation.getId() == null || reservation.getName().isBlank() || reservation.getName().isEmpty()){
            return "The data sent is not valid";
        }

        try{
            DynamoDBMapper dbMapper = new DynamoDBMapper(dynamoDB);
            dbMapper.save(reservation);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Occurred an error inserting the reservation: "+e.getMessage();
        }
        Gson gson = new Gson();

        return gson.toJson(reservation);



    }
}

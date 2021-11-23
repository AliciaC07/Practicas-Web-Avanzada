package org.lab.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import org.lab.models.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReservationServices {


    public String insertReservation(Reservation reservation, Context context){
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();


        if (reservation.getName().isBlank() || reservation.getName().isEmpty()){
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

    public String getReservations(Context context){
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        Gson gson = new Gson();
        List<Reservation> reservations = new ArrayList<>();
        ScanRequest scanRequest = new ScanRequest().withTableName("reservations");
        ScanResult result = null;

        do {
            if (result != null) {
                scanRequest.setExclusiveStartKey(result.getLastEvaluatedKey());
            }
            result = dynamoDB.scan(scanRequest);
            List<Map<String, AttributeValue>> rows = result.getItems();

            for (Map<String, AttributeValue> mapReservations: rows) {
                AttributeValue id = mapReservations.get("id_reservation");
                AttributeValue name = mapReservations.get("name");
                AttributeValue career = mapReservations.get("career");
                AttributeValue lab = mapReservations.get("lab");
                AttributeValue reservationDate = mapReservations.get("reservation_date");
                AttributeValue uniId = mapReservations.get("uni_id");
                AttributeValue email = mapReservations.get("email");
                Reservation reservation = new Reservation();
                reservation.setId(UUID.fromString(id.getS()));
                reservation.setCareer(career.getS());
                reservation.setName(name.getS());
                reservation.setLab(lab.getS());
                reservation.setReservationDate(reservationDate.getS());
                reservation.setEmail(email.getS());
                reservation.setUniId(uniId.getS());
                reservations.add(reservation);

            }

        }while (result.getLastEvaluatedKey() != null);

        return gson.toJson(reservations);
    }

//    public String deleteReservations(String reservation, Context context){
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable("reservations");
//        DeleteItemOutcome outcome = table.deleteItem("id_reservation", reservation.getId());
//        Gson gson = new Gson();
//        return gson.toJson(reservation);
//    }
}

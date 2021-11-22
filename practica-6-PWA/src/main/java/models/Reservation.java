package models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@DynamoDBTable(tableName = "reservations")
public class Reservation {

    @DynamoDBHashKey(attributeName = "id_reservation")
    private Integer id;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "career")
    private String career;
    @DynamoDBAttribute(attributeName = "lab")
    private String lab;
    @DynamoDBAttribute(attributeName = "reservation_date")
    private LocalDate reservationDate;

    public Reservation() {
    }
}

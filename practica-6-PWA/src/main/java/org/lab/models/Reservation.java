package org.lab.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@DynamoDBTable(tableName = "reservations")
public class Reservation {

    @DynamoDBHashKey(attributeName = "id_reservation")
    @DynamoDBGeneratedUuid( DynamoDBAutoGenerateStrategy.CREATE )
    private UUID id;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "career")
    private String career;
    @DynamoDBAttribute(attributeName = "lab")
    private String lab;
    @DynamoDBAttribute(attributeName = "reservation_date")
    private String reservationDate;

    public Reservation() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}

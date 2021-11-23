import com.google.gson.Gson;
import org.lab.models.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Test {

    @org.junit.jupiter.api.Test
    public void prueba(){
        String p = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Alicia\",\n" +
                "    \"career\": \"ISC\",\n" +
                "    \"lab\": \"ISC-517\",\n" +
                "    \"reservationDate\": \"2021-11-11\"\n" +
                "}";
        Gson gson = new Gson();
        Reservation reservation1 = new Reservation();
        reservation1.setName("Ali");
        reservation1.setCareer("ISC");
        //reservation1.setReservationDate(LocalDateTime.now());
        System.out.println(gson.toJson(reservation1));
        Reservation reservation = gson.fromJson(p, Reservation.class);
        System.out.println(reservation.getName());

    }
}

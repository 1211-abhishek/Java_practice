import java.time.*;
import java.time.format.DateTimeFormatter;
public class DateTimeAPIPractice {

    public static void main(String[] args) {
        
        LocalDate localDate = LocalDate.of(2002, 2, 19);
        System.out.println(localDate);

        LocalDate localDate2 = LocalDate.of(2002, Month.FEBRUARY, 19);
        System.out.println(localDate2);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("DD/MM/yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String format1 = localDate.format(formatter1);  // "19/02/2002"
        String format2 = localDate.format(formatter2);  // "50/02/2002"
        String format3 = localDate.format(formatter3);  // "19-02-2002"

        System.out.println(format1);
        System.out.println(format2);
        System.out.println(format3);

        System.out.println(localDate.atStartOfDay());;
    }
    
}

package datavalidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validate {
    public static boolean isPreviousDate(String inputDate) {
    	
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(inputDate);
            Date currentDate = new Date();

            // Check if the parsed date is before the current date
            return date.before(currentDate);
        } catch (ParseException e) {
            // If there is a parsing error, handle it accordingly
            System.out.println("Invalid date format: " + inputDate);
            return false;
        }
    }
    static Pattern PTRN_FOR_DATE = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$"); //date formate update
    public static boolean cal(String inputDate)
    {
    	boolean ck_ptrn=false;
        if (isPreviousDate(inputDate)) {
            System.out.println("The date is a previous date.");
        } else 
        {
        	  ck_ptrn = PTRN_FOR_DATE.matcher(inputDate).matches();
             if (!ck_ptrn) {
                 System.out.println("Time format is invalid. Please use the format HH:MM. Kindly try again.");
             }
             return ck_ptrn;
        }
        return ck_ptrn;
    }
    public static void main(String[] args)
    {
        String inputDate = "05/02/2025";  // Replace with your input date
   	    boolean b=cal(inputDate);
   	    System.out.println(b);
}
}

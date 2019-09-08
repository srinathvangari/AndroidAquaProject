package com.ssvtech.jalforce1.Utility;

import java.util.Date;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

public class DateUtility {

public static String convertToDDMMMMYYYY(String dateValue){
    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");


    try{
        SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
        Date date1=formatter.parse(dateValue);

        String strDate= formatter1.format(date1);
        System.out.println(strDate);

        return strDate;
    }catch(Exception e){
       System.out.println("EXCEPTIOn FOR " +dateValue + "  exaception : "+e.getStackTrace().toString());
        System.out.println("Exception e : "+e);
    }

    return null;

}


}

package com.bbeerbear.grpccollegecost.client.util;

import com.bbeerbear.grpcclooegecost.server.PAST_YEARS;
import com.bbeerbear.grpccollegecost.common.Expense;
import com.bbeerbear.grpccollegecost.common.Length;
import com.bbeerbear.grpccollegecost.common.Type;

public class EnumTransferUtil {
    public static Type typeTransfer(String typeString){
        Type type = null;
        switch (typeString){
            case "Private" -> type = Type.PRIVATE;
            case "Public In-State" -> type = Type.PUBLIC_IN_STATE;
            case "Public Out-of-State" -> type = Type.PUBLIC_OUT_OF_STATE;
        }
        return type;
    }
    public static Length lengthTransfer(String lengthString){
        Length length = null;
        switch (lengthString) {
            case "2-year" -> length = Length._2_YEAR;
            case "4-year" -> length = Length._4_YEAR;
        }
        return length;
    }
    public static Expense expenseTransfer(String expenseString){
        Expense expense = null;
        switch (expenseString) {
            case "Fees/Tuition" -> expense = Expense.FEES_TUITION;
            case "Room/Board" -> expense = Expense.ROOM_BOARD;
        }
        return expense;
    }
    public static PAST_YEARS pastYearsTransfer(int pastYearInt){
        PAST_YEARS pastYears = null;
        switch (pastYearInt) {
            case 1 -> pastYears = PAST_YEARS.ONE_YEAR;
            case 3 -> pastYears = PAST_YEARS.THREE_YEARS;
            case 5 -> pastYears = PAST_YEARS.FIVE_YEARS;
        }
        return pastYears;
    }
}

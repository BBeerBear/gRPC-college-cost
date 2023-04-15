package com.bbeerbear.grpccollegecost.educoststatquery.utils;

import com.bbeerbear.grpcclooegecost.server.PAST_YEARS;
import com.bbeerbear.grpccollegecost.common.Expense;
import com.bbeerbear.grpccollegecost.common.Length;
import com.bbeerbear.grpccollegecost.common.Type;

public class EnumTransferUtil {
    public static String lengthTransfer(Length length){
        String formattedLength = null;
        switch (length) {
            case _2_YEAR -> formattedLength = "2-year";
            case _4_YEAR -> formattedLength = "4-year";
        }
        return formattedLength;
    }
    public static String typeTransfer(Type type){
        String formattedType = null;
        switch (type){
            case PRIVATE -> formattedType = "Private";
            case PUBLIC_IN_STATE -> formattedType = "Public In-State";
            case PUBLIC_OUT_OF_STATE -> formattedType = "Public Out-Of-State";
        }
        return formattedType;
    }
    public static String expenseTransfer(Expense expense){
        String formattedExpense = null;
        switch (expense) {
            case FEES_TUITION -> formattedExpense = "Fees/Tuition";
            case ROOM_BOARD -> formattedExpense = "Room/Board";
        }
        return formattedExpense;
    }
    public static int pastYearsTransfer(PAST_YEARS pastYears){
        int formattedPastYears = 0;
        switch (pastYears){
            case ONE_YEAR -> formattedPastYears = 1;
            case THREE_YEARS -> formattedPastYears = 2;
            case FIVE_YEARS -> formattedPastYears = 3;
        }
        return formattedPastYears;
    }
}

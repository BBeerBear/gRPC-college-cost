package com.bbeerbear.grpccollegecost.jms.consumer;



import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultFive;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultFour;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultOne;
import com.bbeerbear.grpccollegecost.aggregator.dto.EduStatQueryResultTwo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomMessageListener {
//    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);

    @RabbitListener(queues = "Cost-[Year]-[State]-[Type]-[Length]")
    public void getMessage1(List<EduStatQueryResultOne> eduStatQueryResultOne) {
        System.out.println("Query the cost given specific year, state, type, length, expense");
        System.out.println(eduStatQueryResultOne.toString());
    }
    @RabbitListener(queues = "Top5-Expensive-[Year]-[Type]-[Length]")
    public void getMessage2(List<EduStatQueryResultTwo> eduStatQueryResultTwos) {
        System.out.println("Query the top 5 most expensive states (with overall expense) " +
                "given a year, type, length");
        for (EduStatQueryResultTwo eduStatQueryResultTwo : eduStatQueryResultTwos){
            System.out.println(eduStatQueryResultTwo.toString());
        }
    }
    @RabbitListener(queues = "Top5-Economic-[Year]-[Type]-[Length]")
    public void getMessage3(List<EduStatQueryResultTwo> eduStatQueryResultThrees) {
        System.out.println("Query the top 5 most economic states (with overall expense) " +
                "given a year, type, length");
        for (EduStatQueryResultTwo eduStatQueryResultThree : eduStatQueryResultThrees){
            System.out.println(eduStatQueryResultThree.toString());
        }
    }
    @RabbitListener(queues = "Top5-HighestGrow-[Years]")
    public void getMessage4(List<EduStatQueryResultFour> eduStatQueryResultFours) {
        System.out.println("Query the top 5 states of the highest growth rate of " +
                "overall expense given a range of past years, one year, three years and five years " +
                "(using the latest year as the base) , type and length");
        for (EduStatQueryResultFour eduStatQueryResultFour : eduStatQueryResultFours){
            System.out.println(eduStatQueryResultFour.toString());
        }
    }
    @RabbitListener(queues = "AverageExpense-[Year]-[Type]-[Length]")
    public void getMessage5(List<EduStatQueryResultFive> eduStatQueryResultFives) {
        System.out.println("Aggregate region‘s average overall expense for a given year," +
                " type and length");
        for (EduStatQueryResultFive eduStatQueryResultFive : eduStatQueryResultFives){
            System.out.println(eduStatQueryResultFive.toString());
        }
    }

//    @RabbitListener(queues = MessagingApplication.QUEUE_SPECIFIC_NAME)
//    public void receiveMessage(final CustomMessage customMessage) {
//        log.info("Received message and deserialized to 'CustomMessage': {}", customMessage.toString());
//    }
}

package com.bridgelabz;

//import com.bridgelabz.RideRepositary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() throws Exception {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void giveDistanceAndTime_Should_RetureTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMiniFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1,1)
                        };
        InvoiceSummary summary =  invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
    @Test
    public void GivenUserId_ShouldReturnInvoiceSummary() {
        String userID = "abc@gmail.com";
        Ride[] rides = { new Ride(2.0,5), new Ride(5.0,4),
                new Ride(0.1,1)};
        invoiceGenerator.addRides(userID,rides);
        InvoiceSummary summary=invoiceGenerator.calculateFare(rides, "normal");
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userID);
        Assert.assertEquals(invoiceSummary, summary);
    }


}
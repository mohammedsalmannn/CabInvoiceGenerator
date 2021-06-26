package com.bridgelabz;





public class InvoiceGenerator {

    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_FARE = 5;
    private static final int numOfRide = 5;
    RideRepository rideRepository = new RideRepository();

    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        if (totalFare < MINIMUM_FARE)
            return MINIMUM_FARE;
        return totalFare;
    }

    public Object ride(double distance, int time) {
        return null;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalfare = 0;
        for (Ride ride : rides) {
            totalfare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalfare);
    }

/*    public double calculateFares(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        if (totalFare < MINIMUM_FARE)
            return MINIMUM_FARE;
        return totalFare;
    }*/

    // public double calculateFare(double distance, int time) {
    //   ArrayList list = new ArrayList();
    // double fare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME ;
//        list.add(fare);
//        list.stream().filter(total ->  total.equals(user));
//        for (Object ride: list) {
//            if (ride.equals(user)){
//                double totalFair  = totalFair + totalFair;
//            }


    //return 0;
    // }
    public InvoiceSummary calculateFare(Ride[] rides, String type) {
        double totalFare = 0;
        if (type == "premium") {
            for (Ride ride : rides) {
                totalFare += this.calculateFare(ride.distance, ride.time);
            }
            return new InvoiceSummary(rides.length, totalFare);
        }
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId), "normal");
    }

}
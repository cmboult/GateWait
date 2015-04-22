package waittimecalculator;

public class Customer
{
   private double arrivalTime;
   /** 
       Constructs a customer.
       @param the time at which the customer joined the queue
   */
   public Customer(double time) { arrivalTime = time; }

   /**
      Gets the time at which the customer joined the queue
      @return the arrival time
   */
   double getArrivalTime() { return arrivalTime; }
}
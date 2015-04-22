package waittimecalculator;

import java.util.LinkedList;
import java.util.Queue;

/**
   Simulation of customer traffic in a bank.
*/
public class QueueSimulation extends Simulation
{
   private Customer[] tellers;
   private Queue<Customer> custQueue;

   private int totalCustomers;
   private double totalTime;

   private double INTERARRIVAL; 
      // lambda
   private static final double PROCESSING = 2.8; 
      // mu

   public QueueSimulation(double numberOfPassengers) 
   {
	  this.INTERARRIVAL = 180 / numberOfPassengers; 
      tellers = new Customer[13]; 
      custQueue = new LinkedList<Customer>();
      totalCustomers = 0;
      totalTime = 0;
   }

   /** 
       Adds a customer to the queue.
       @param c the customer
   */
   public void add(Customer c) 
   {
      boolean addedToTeller = false;
      for (int i = 0; !addedToTeller && i < tellers.length; i++)
      {
         if (tellers[i] == null)
         {  
            addToService(i, c);
            addedToTeller = true;
         }
      }
      if (!addedToTeller) { custQueue.add(c); }

      addEvent(new Arrival(getCurrentTime() + expdist(INTERARRIVAL)));
   }

   /**
      Adds a customer to a teller and schedules the departure event.
      @param i the teller number
      @param c the customer
   */
   private void addToService(int i, Customer c)
   {
      tellers[i] = c;
      addEvent(new Departure(getCurrentTime() + expdist(PROCESSING), i));
   }
   
   /** 
       Removes a customer from the queue.
       @param i teller position
   */
   public void remove(int i)
   {
      Customer c = tellers[i];
      tellers[i] = null;

      // Update statistics
      totalCustomers++; 
      totalTime = totalTime + getCurrentTime() - c.getArrivalTime();

      if (custQueue.size() > 0)
      {
         addToService(i, custQueue.remove());
      }
   }

   /** 
       Displays a summary of the gathered statistics.
   */
   public double calculateResults()
   {    
         return totalTime / totalCustomers; 
   }
}
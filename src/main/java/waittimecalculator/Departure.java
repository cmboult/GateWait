package waittimecalculator;

public class Departure extends Event
{
   private int teller;

   /**
      @param time. the departure time
      @param teller. the teller holding the customer
   */
   public Departure(double time, int teller)
   {
      super(time);
      this.teller = teller;
   }
   
   public void process(Simulation sim)
   {  
      QueueSimulation bank = (QueueSimulation) sim;
      bank.remove(teller);
   }
}

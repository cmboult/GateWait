package waittimecalculator;

public class Arrival extends Event
{
   /**
      @param time the arrival time
   */
   public Arrival(double time) {
	   super(time); 
   }

   public void process(Simulation sim)
   {
      double now = sim.getCurrentTime();
      QueueSimulation bank = (QueueSimulation) sim;
      Customer c = new Customer(now);
      bank.add(c);
   }
}

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class RFQGenerator extends SwapPrice {
    public double amount = 0;
    public String direction = "BUY";
    public String logger_file = "rfq-history.log";
    public boolean _running = true;

    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    public void _logging() {
        try {
            fh = new FileHandler(this.logger_file);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            String log_str = String.format("10y Swap Price = %.2f%% %n", this.swap_price);
            log_str = log_str + String.format("Amount = %.2f %n", this.amount);
            log_str = log_str + "Direction = " + this.direction + "\n";

            logger.info(log_str);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void random_rfq_order() {
        this.amount = (new Random()).nextInt(99) + 1;
        String choices[] = {"BUY", "SELL"};
        this.direction = choices[(new Random()).nextInt(2)];
        return;
    }

    public void rfq_generator(){
        int counter = 0;
        while (_running) {
            this.random_rfq_order();
            try {
                TimeUnit.SECONDS.sleep((new Random()).nextInt(4) + 1);
            } catch (InterruptedException e) {;}
            this._logging();
            counter++;
            if (counter == 20) {
                this._running = false;
            }
        }
        return;
    }

    public void run(){
        this.rfq_generator();
    }
}

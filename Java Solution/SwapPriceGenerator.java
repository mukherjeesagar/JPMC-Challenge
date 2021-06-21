import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SwapPriceGenerator extends SwapPrice {
    public double swap_spread = 0.00;
    public double bond_rate = 4.50;
    public boolean _running = true;

    public void random_price() {
        this.bond_rate = 4.50 + 0.10*(new Random().nextDouble());
        this.swap_spread = 0.50 + 0.60*(new Random().nextDouble());
        setSwapPrice(this.swap_spread + this.bond_rate);
        return;
    }

    public void price_generator() {
        int counter = 0;
        while (_running) {
            this.random_price();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {;}
            counter++;
            if (counter == 100) {
                this._running = false;
            }
        }
        return;
    }

    public void run(){
        this.price_generator();
    }
}

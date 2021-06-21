import time, _thread
import logging
from random import uniform, choices

swap_price = 4.50

class SwapPriceGenerator:
    def __init__(self):
        self.swap_price = 4.50
        self.swap_spread = 0.00
        self.bond_rate = 4.50
        self._running = True
    
    def random_price(self):
        global swap_price
        self.bond_rate = round(uniform(4.50, 4.60), 2)
        self.swap_spread = round(uniform(0.50, 1.10), 2)
        self.swap_price = round(self.swap_spread + self.bond_rate, 2)
        swap_price = self.swap_price
        return
        
    def price_generator(self):
        counter = 0
        while self._running:
            self.random_price()
            time.sleep(1)
            counter += 1
            if counter == 100:
                self._running = False
        return

class RFQGenerator:
    def __init__(self):
        self.amount = 0
        self.swap_price = 4.50
        self.direction = 'BUY'
        self.logger_file = "rfq-history.log"
        self._running = True
        
        logging.basicConfig(filename=self.logger_file,
                            format='%(asctime)s %(message)s',
                            filemode='w')
        logger = logging.getLogger()
        logger.setLevel(logging.INFO)
        logging.info("10y Swap Price, Amount, Direction")
    
    def __logging__(self):
        log_str = str(self.swap_price) + '%\t\t'
        log_str += str(self.amount) + '\t'
        log_str += str(self.direction)
        logging.info(log_str)
    
    def random_rfq_order(self):
        self.direction = choices(["BUY", "SELL"])[0]
        self.amount = int(uniform(1,100))
        return
    
    def request_quote(self):
        global swap_price
        self.swap_price = swap_price
        print("Current 10y swap price =", self.swap_price, "%")
        return
    
    def rfq_generator(self):
        counter = 0
        while self._running:
            self.random_rfq_order()
            self.request_quote()
            self.__logging__()
            time.sleep(uniform(1,5))
            counter += 1
            if counter == 100:
                self._running = False
        return
        
    
def main():
    sp_gen = SwapPriceGenerator()
    rfq_gen = RFQGenerator()
    _thread.start_new_thread(sp_gen.price_generator, ())
    _thread.start_new_thread(rfq_gen.rfq_generator, ())    
    
if __name__ == "__main__":
    main()
    

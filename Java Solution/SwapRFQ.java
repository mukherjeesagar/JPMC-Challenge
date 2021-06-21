public class SwapRFQ {
    public static void main(String args[]) {

        SwapPriceGenerator t1=new SwapPriceGenerator();
        RFQGenerator t2=new RFQGenerator();

        t1.start();
        t2.start();
    }
}

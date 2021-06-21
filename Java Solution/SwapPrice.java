class SwapPrice extends Thread{
    public static double swap_price = 4.50;

    public void setSwapPrice(double x) {
        this.swap_price = x;
        System.out.printf("Current 10y swap price = %.2f%% \n", this.swap_price);
        return;
    }
}

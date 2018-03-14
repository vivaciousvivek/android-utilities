package dpi;

public class DPITest {
  public static void main(String[] args) {
    /*
     * Pixel to DP
     */
    DPICalculator dpiCalculator = new DPICalculator(true, 150, 150, 160);
    dpiCalculator.calculate();

    /*
     * Standard size of Android Launcher Icon size
     * DP to Pixel
     */
    DPICalculator dpiCalculator1 = new DPICalculator(false, 48, 48, 160);
    dpiCalculator1.calculate();
  }
}

package dpi;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018 - 03 - 13 15 : 36 : 59)
 */
public class DPICalculator {
  private final float LDPI = 120;
  private final float MDPI = 160;
  private final float HDPI = 240;
  private final float XHDPI = 320;
  private final float XXHDPI = 480;
  private final float XXXHDPI = 640;

  private Set<Float> standardDPIs =
      Stream.of(LDPI, MDPI, HDPI, XHDPI, XXHDPI, XXXHDPI).collect(Collectors.toSet());
  private final boolean isDPI;

  private float width;
  private float height;
  private float baseDPI;

  /**
   * @param isDPI boolean variable to identify which one user want to calculate DP -> Pixel or Pixel
   *     -> DP
   * @param width image/device width in pixel(i.e in resolution)
   * @param height image/device width in pixel(i.e in resolution)
   * @param baseDPI if any base DPI on that basis you want to calculate, it should be less than or
   *     equal to the targetDeviceDPI, if you don't specify then by default will take 160DPI as a
   *     base
   */
  public DPICalculator(boolean isDPI, float width, float height, float baseDPI) {
    this.isDPI = isDPI;
    this.width = width;
    this.height = height;

    if (baseDPI > 160.0f) this.baseDPI = baseDPI;
    else this.baseDPI = 160.0f;
  }

  public void calculate() {
    if (isDPI) printAllDensityPixelsFromPixel();
    else printAllPixelsFromDensityPixel();
  }

  public void printAllPixelsFromDensityPixel() {
    StringBuilder sb = new StringBuilder("\nStarted from LDPI to XXXHDPI in Pixel :");
    standardDPIs.forEach(
        dp ->
            sb.append("\n : ")
                .append(getPixelFromDensityPixel(dp, width))
                .append(" X ")
                .append(getPixelFromDensityPixel(dp, height)));

    System.out.println(sb);
  }

  public void printAllDensityPixelsFromPixel() {
    StringBuilder sb = new StringBuilder("\nStarted from LDPI to XXXHDPI in DP :");
    standardDPIs.forEach(
        dp ->
            sb.append("\n : ")
                .append(getDensityPixelFromPixel(width, dp))
                .append(" X ")
                .append(getDensityPixelFromPixel(height, dp)));

    System.out.println(sb);
  }

  /**
   * @param dp Density pixel to convert it into Pixel for target device DPI using base DPI(takes 160
   *     by default when not provided)
   * @return pixel of given dp
   */
  private float getPixelFromDensityPixel(float dp, float targetDeviceDPI) {
    return dp * (targetDeviceDPI / this.baseDPI);
  }

  /**
   * @param px Pixel to convert it into Density Pixel for target device DPI using base DPI(takes 160
   *     by default when not provided)
   * @return dp of given pixel
   */
  private float getDensityPixelFromPixel(float px, float targetDeviceDPI) {
    return px * (this.baseDPI / targetDeviceDPI);
  }
}

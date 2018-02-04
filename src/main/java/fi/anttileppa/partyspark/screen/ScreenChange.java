package fi.anttileppa.partyspark.screen;

public class ScreenChange {

  private Pixel pixel;

  public ScreenChange() {
  }

  public ScreenChange(Pixel pixel) {
    super();
    this.pixel = pixel;
  }

  public Pixel getPixel() {
    return pixel;
  }

  public void setPixel(Pixel pixel) {
    this.pixel = pixel;
  }

}

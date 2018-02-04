package fi.anttileppa.partyspark.screen;

import java.awt.Color;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Pixel {

  private int x;
  private int y;
  private Color color;

  public Pixel() {
  }

  public Pixel(int x, int y, Color color) {
    super();
    this.x = x;
    this.y = y;
    this.color = color;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
  
  @JsonIgnore
  public String toRgb() {
    if (color.getAlpha() >= 255) {
      return String.format("rgb(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    return String.format("rgba(%d, %d, %d, %f)", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() / 255f);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Pixel)) {
      return false;
    }
    
    Pixel another = (Pixel) obj;
    
    return another.getX() == this.getX() && 
        another.getY() == this.getY() &&
        another.getColor().equals(this.getColor());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY(), getColor());
  }

}

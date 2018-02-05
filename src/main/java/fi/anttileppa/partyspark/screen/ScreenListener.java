package fi.anttileppa.partyspark.screen;

import java.util.List;

public interface ScreenListener {

  public void onChange(List<ScreenChange> changes);
  
}

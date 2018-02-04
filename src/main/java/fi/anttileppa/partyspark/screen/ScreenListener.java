package fi.anttileppa.partyspark.screen;

import java.util.Set;

public interface ScreenListener {

  public void onChange(Set<ScreenChange> changes);
  
}

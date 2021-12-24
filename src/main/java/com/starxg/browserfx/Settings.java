package com.starxg.browserfx;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Peter Wu
 */
@com.intellij.openapi.components.State(
    name = "browserfx",
    storages = @Storage("browserfx.settings.xml")
)
public class Settings implements PersistentStateComponent<Settings> {

  private String url;

  public static Settings getInstance() {
    return ApplicationManager.getApplication().getService(Settings.class);
  }

  public Settings() {
    this.url = "about:blank";
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public @Nullable Settings getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull Settings state) {
    this.url=state.url;
  }
}

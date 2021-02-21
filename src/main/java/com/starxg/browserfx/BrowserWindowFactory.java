package com.starxg.browserfx;

import java.lang.reflect.Method;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.ReflectionUtil;

/**
 * 工厂
 * 
 * @author huangxingguang
 * @date 2019-04-21 14:55
 */
public class BrowserWindowFactory implements ToolWindowFactory {

    public BrowserWindowFactory() {

    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        BrowserView browserView;
        if (isSupportedJBCef()) {
            browserView = new JcefBrowser();
        } else {
            browserView = new JavaFxBrowser();
        }
        System.out.println(isSupportedJBCef());
        Content content = contentFactory.createContent(new Browser(browserView), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private boolean isSupportedJBCef() {
        try {
            Method method = ReflectionUtil.getDeclaredMethod(Class.forName("com.intellij.ui.jcef.JBCefApp"),
                    "isSupported");
            return Objects.nonNull(method) && (boolean) method.invoke(null);
        } catch (Exception e) {
            return false;
        }
    }
}
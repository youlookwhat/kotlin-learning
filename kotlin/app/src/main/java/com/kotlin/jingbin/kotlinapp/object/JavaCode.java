package com.kotlin.jingbin.kotlinapp.object;

import org.jetbrains.annotations.NotNull;

public class JavaCode {

    // 代码清单 4.10 用带内部类的 Java 代码来实现 View
    public class Button implements ObjectActivity.View {

        @NotNull
        @Override
        public ObjectActivity.State getCurrentState() {
            return new ButtonState();
        }

        @Override
        public void restoreState(@NotNull ObjectActivity.State state) {

        }

        public class ButtonState implements ObjectActivity.State {
        }
    }

}

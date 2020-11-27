package com.kotlin.jingbin.kotlinapp.object;

import org.jetbrains.annotations.NotNull;

public class JavaCode implements Object2Activity.User9 {

    public void postponeComputation(int delay,Runnable computation){

    }

    @NotNull
    @Override
    public String getName() {
        return "";
    }

    @NotNull
    @Override
    public String getEmail() {
        return null;
    }

    // 代码清单 4.10 用带内部类的 Java 代码来实现 View
    public class Button implements Object1Activity.View {

        @NotNull
        @Override
        public Object1Activity.State getCurrentState() {
            return new ButtonState();
        }

        @Override
        public void restoreState(@NotNull Object1Activity.State state) {

        }

        public class ButtonState implements Object1Activity.State {
        }


//        Object4Activity.CaseInsensitiveFileComparator.INSTANCE.compare()
    }

    interface User {
//        public String ddd;
    }
}

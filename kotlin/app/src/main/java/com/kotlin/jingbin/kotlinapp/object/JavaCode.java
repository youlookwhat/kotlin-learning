package com.kotlin.jingbin.kotlinapp.object;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class JavaCode implements Object2Activity.User9 {

    public void postponeComputation(int delay, Runnable computation) {

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

    public static class PersonJava {

        private final String name;

        public PersonJava(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public static interface StringProcess {
        void process(String value);
    }

    public static class CollectionUtils {
        public static List<String> uppercaseAll(List<String> items) {
            for (int i = 0; i < items.size(); i++) {
                items.set(i, items.get(i).toLowerCase());
            }
            return items;
        }
    }

    public interface FileContentProcessor {
        void processContents(File path, byte[] binaryContents, List<String> textContents);
    }

    public interface DataParser<T> {
        void parseData(String input, List<T> output, List<String> errors);
    }
}

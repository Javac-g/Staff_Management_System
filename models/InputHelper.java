package models;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputHelper {
    public record FieldPrompt<T>(String prompt, Supplier<T> reader, Consumer<T> validator) {}

    public static <T> T promptWithValidation(Supplier<T> inputSupplier, Consumer<T> validator, String errorMessage) {
        while (true) {
            try {
                T value = inputSupplier.get();
                validator.accept(value);
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage + ": " + e.getMessage());
            }
        }
    }
    public static <T> T prompt(FieldPrompt<T> fp) {
        return promptWithValidation(
                fp.reader(),
                fp.validator(),
                fp.prompt()
        );
    }
}

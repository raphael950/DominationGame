package net.hydramc.util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class FieldReflection {

    public static Field getModifierField() {
        final Field field;

        try {
            field = Field.class.getDeclaredField("modifiers");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException ignored) {}
        return null;

    }

    public static Field getField(String name, Class<?> clazz) {
        final Field field;
        final Field modifierField;

        if (name == null || clazz == null)
            return null;
        modifierField = getModifierField();
        if (modifierField == null)
            return null;
        try {
            field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            modifierField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            return field;
        } catch (Exception ignored) {}
        return null;
    }

    public static Optional<Object> getFieldOptionalValue(String name, Class<?> clazz, Object object) {
        final Field field = getField(name, clazz);
        final Object result;

        if (field == null)
            return null;
        try {
            return Optional.ofNullable(field.get(object));
        } catch (IllegalAccessException ignored) {}
        return null;
    }

    public static Object getFieldValue(String name, Class<?> clazz, Object object) {
        final Optional<Object> result = getFieldOptionalValue(name, clazz, object);

        if (result == null || !result.isPresent())
            return null;
        return result.get();
    }

    public static boolean setFieldValue(String name, Class<?> clazz, Object object, Object value) {
        final Field field = getField(name, clazz);

        if (field == null)
            return false;
        try {
            field.set(object, value);
            return true;
        } catch (IllegalAccessException ignored) {}
        return false;
    }
}

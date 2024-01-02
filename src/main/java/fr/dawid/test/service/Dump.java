package fr.dawid.test.service;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Dump {
    private static String getPackagesProject() {
        String packages = Dump.class.getPackageName();
        String[] myPackage = packages.split("\\.");
        return myPackage[0] + "." + myPackage[1] + "." + myPackage[2];
    }

    public static void test(Object object) {
        System.out.println(dumper(object, 1));
    }

    private static String dumper(Object object, int level) {
        if (object == null) {
            return "";
        }
        if (object instanceof String) {
            return (String) object;
        }
        Class<?> objectClass = object.getClass();
        StringBuilder sb = new StringBuilder(objectClass.getSimpleName());
        sb.append("\n");
        sb.append("{");
        sb.append("\n");
        for (Field field : objectClass.getDeclaredFields()) {
            try {
                sb.append("\t");
                sb.append(field.getName());
                sb.append(" : ");
                String upperCaseFieldName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                String name = "get" + upperCaseFieldName;
                if (field.getType().getSimpleName().equals("boolean")) {
                    name = field.getName();
                }
                Method getter = objectClass.getDeclaredMethod(name);
                Object o = getter.invoke(object);
                if (o instanceof List) {
                    sb.append("[");
                    sb.append("\n");
                    for (Object item : (List<?>) o) {
                        if (!item.getClass().getPackageName().equals(object.getClass().getPackageName())) {
                            sb.append(dumper(item, ++level));
                        }
                    }
                    sb.append("\n");
                    sb.append("\t]");
                    sb.append("\n");
                } else if (o != null && o.getClass().getPackageName().startsWith(getPackagesProject())) {
                    if (!o.getClass().getPackageName().equals(object.getClass().getPackageName())) {
                        sb.append(dumper(o, ++level));
                    }
                }
                sb.append(o);
                sb.append("\n");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("Getter not found :(");
            }
        }
        sb.append("}");
        sb.append("\n");
        return sb.toString();
    }

    public static void json(String json) {
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.toString(4)); // Print it with specified indentation
    }

    public static void dump(Object o) {
        JSONObject jsonObject = new JSONObject(o);
        String str = o.getClass().getSimpleName() + " " + jsonObject.toString(4);
        System.out.println(str);
    }
}

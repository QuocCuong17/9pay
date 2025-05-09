package com.google.crypto.tink.shaded.protobuf;

import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final char[] INDENT_BUFFER;
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    static {
        char[] cArr = new char[80];
        INDENT_BUFFER = cArr;
        Arrays.fill(cArr, ' ');
    }

    private MessageLiteToString() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(MessageLite messageLite, String commentString) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(commentString);
        reflectivePrintWithIndent(messageLite, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x016f, code lost:
    
        if (r5.containsKey(co.hyperverge.hyperkyc.data.models.WorkflowRequestType.Method.GET + r9.substring(0, r9.length() - 5)) != false) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder buffer, int indent) {
        int i;
        int i2;
        boolean booleanValue;
        java.lang.reflect.Method method;
        java.lang.reflect.Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        java.lang.reflect.Method[] declaredMethods = messageLite.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i3 = 0;
        while (true) {
            i = 3;
            if (i3 >= length) {
                break;
            }
            java.lang.reflect.Method method3 = declaredMethods[i3];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith(WorkflowRequestType.Method.GET)) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i3++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i);
            if (substring.endsWith("List") && !substring.endsWith(BUILDER_LIST_SUFFIX) && !substring.equals("List") && (method2 = (java.lang.reflect.Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                printField(buffer, indent, substring.substring(0, substring.length() - 4), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]));
                i = 3;
            } else {
                if (substring.endsWith(MAP_SUFFIX) && !substring.equals(MAP_SUFFIX) && (method = (java.lang.reflect.Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                    i2 = 3;
                    printField(buffer, indent, substring.substring(0, substring.length() - 3), GeneratedMessageLite.invokeOrDie(method, messageLite, new Object[0]));
                } else {
                    i2 = 3;
                    if (hashSet.contains("set" + substring)) {
                        if (substring.endsWith(BYTES_SUFFIX)) {
                        }
                        java.lang.reflect.Method method4 = (java.lang.reflect.Method) entry.getValue();
                        java.lang.reflect.Method method5 = (java.lang.reflect.Method) hashMap.get("has" + substring);
                        if (method4 != null) {
                            Object invokeOrDie = GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[0]);
                            if (method5 == null) {
                                booleanValue = !isDefaultValue(invokeOrDie);
                            } else {
                                booleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method5, messageLite, new Object[0])).booleanValue();
                            }
                            if (booleanValue) {
                                printField(buffer, indent, substring, invokeOrDie);
                            }
                        }
                    }
                }
                i = i2;
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> it = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it.hasNext()) {
                Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next = it.next();
                printField(buffer, indent, "[" + next.getKey().getNumber() + "]", next.getValue());
            }
        }
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) messageLite;
        if (generatedMessageLite.unknownFields != null) {
            generatedMessageLite.unknownFields.printWithIndent(buffer, indent);
        }
    }

    private static boolean isDefaultValue(Object o) {
        if (o instanceof Boolean) {
            return !((Boolean) o).booleanValue();
        }
        if (o instanceof Integer) {
            return ((Integer) o).intValue() == 0;
        }
        if (o instanceof Float) {
            return Float.floatToRawIntBits(((Float) o).floatValue()) == 0;
        }
        if (o instanceof Double) {
            return Double.doubleToRawLongBits(((Double) o).doubleValue()) == 0;
        }
        if (o instanceof String) {
            return o.equals("");
        }
        if (o instanceof ByteString) {
            return o.equals(ByteString.EMPTY);
        }
        return o instanceof MessageLite ? o == ((MessageLite) o).getDefaultInstanceForType() : (o instanceof java.lang.Enum) && ((java.lang.Enum) o).ordinal() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printField(StringBuilder buffer, int indent, String name, Object object) {
        if (object instanceof List) {
            Iterator it = ((List) object).iterator();
            while (it.hasNext()) {
                printField(buffer, indent, name, it.next());
            }
            return;
        }
        if (object instanceof Map) {
            Iterator it2 = ((Map) object).entrySet().iterator();
            while (it2.hasNext()) {
                printField(buffer, indent, name, (Map.Entry) it2.next());
            }
            return;
        }
        buffer.append('\n');
        indent(indent, buffer);
        buffer.append(pascalCaseToSnakeCase(name));
        if (object instanceof String) {
            buffer.append(": \"");
            buffer.append(TextFormatEscaper.escapeText((String) object));
            buffer.append('\"');
            return;
        }
        if (object instanceof ByteString) {
            buffer.append(": \"");
            buffer.append(TextFormatEscaper.escapeBytes((ByteString) object));
            buffer.append('\"');
            return;
        }
        if (object instanceof GeneratedMessageLite) {
            buffer.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) object, buffer, indent + 2);
            buffer.append(IOUtils.LINE_SEPARATOR_UNIX);
            indent(indent, buffer);
            buffer.append("}");
            return;
        }
        if (object instanceof Map.Entry) {
            buffer.append(" {");
            Map.Entry entry = (Map.Entry) object;
            int i = indent + 2;
            printField(buffer, i, "key", entry.getKey());
            printField(buffer, i, "value", entry.getValue());
            buffer.append(IOUtils.LINE_SEPARATOR_UNIX);
            indent(indent, buffer);
            buffer.append("}");
            return;
        }
        buffer.append(": ");
        buffer.append(object);
    }

    private static void indent(int indent, StringBuilder buffer) {
        while (indent > 0) {
            char[] cArr = INDENT_BUFFER;
            int length = indent > cArr.length ? cArr.length : indent;
            buffer.append(cArr, 0, length);
            indent -= length;
        }
    }

    private static String pascalCaseToSnakeCase(String pascalCase) {
        if (pascalCase.isEmpty()) {
            return pascalCase;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(pascalCase.charAt(0)));
        for (int i = 1; i < pascalCase.length(); i++) {
            char charAt = pascalCase.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}

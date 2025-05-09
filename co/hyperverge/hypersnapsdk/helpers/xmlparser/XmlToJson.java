package co.hyperverge.hypersnapsdk.helpers.xmlparser;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes2.dex */
public class XmlToJson {
    private static final String DEFAULT_CONTENT_NAME = "content";
    private static final boolean DEFAULT_EMPTY_BOOLEAN = false;
    private static final double DEFAULT_EMPTY_DOUBLE = 0.0d;
    private static final int DEFAULT_EMPTY_INTEGER = 0;
    private static final long DEFAULT_EMPTY_LONG = 0;
    private static final String DEFAULT_EMPTY_STRING = "";
    private static final String DEFAULT_ENCODING = "utf-8";
    private static final String DEFAULT_INDENTATION = "   ";
    private static final String TAG = "XmlToJson";
    private final HashMap<String, String> mAttributeNameReplacements;
    private final HashMap<String, String> mContentNameReplacements;
    private final HashMap<String, Class> mForceClassForPath;
    private final HashSet<String> mForceListPaths;
    private String mIndentationPattern;
    private final String mInputEncoding;
    private final InputStream mInputStreamSource;
    private final JSONObject mJsonObject;
    private HashSet<String> mSkippedAttributes;
    private HashSet<String> mSkippedTags;
    private final StringReader mStringSource;

    /* loaded from: classes2.dex */
    public static class Builder {
        private final HashMap<String, String> mAttributeNameReplacements;
        private final HashMap<String, String> mContentNameReplacements;
        private final HashMap<String, Class> mForceClassForPath;
        private final HashSet<String> mForceListPaths;
        private String mInputEncoding;
        private InputStream mInputStreamSource;
        private final HashSet<String> mSkippedAttributes;
        private final HashSet<String> mSkippedTags;
        private StringReader mStringSource;

        public Builder(String str) {
            this.mInputEncoding = XmlToJson.DEFAULT_ENCODING;
            this.mForceListPaths = new HashSet<>();
            this.mAttributeNameReplacements = new HashMap<>();
            this.mContentNameReplacements = new HashMap<>();
            this.mForceClassForPath = new HashMap<>();
            this.mSkippedAttributes = new HashSet<>();
            this.mSkippedTags = new HashSet<>();
            this.mStringSource = new StringReader(str);
        }

        public Builder(InputStream inputStream, String str) {
            this.mInputEncoding = XmlToJson.DEFAULT_ENCODING;
            this.mForceListPaths = new HashSet<>();
            this.mAttributeNameReplacements = new HashMap<>();
            this.mContentNameReplacements = new HashMap<>();
            this.mForceClassForPath = new HashMap<>();
            this.mSkippedAttributes = new HashSet<>();
            this.mSkippedTags = new HashSet<>();
            this.mInputStreamSource = inputStream;
            this.mInputEncoding = str == null ? XmlToJson.DEFAULT_ENCODING : str;
        }

        public Builder forceList(String str) {
            this.mForceListPaths.add(str);
            return this;
        }

        public Builder setAttributeName(String str, String str2) {
            this.mAttributeNameReplacements.put(str, str2);
            return this;
        }

        public Builder setContentName(String str, String str2) {
            this.mContentNameReplacements.put(str, str2);
            return this;
        }

        public Builder forceIntegerForPath(String str) {
            this.mForceClassForPath.put(str, Integer.class);
            return this;
        }

        public Builder forceLongForPath(String str) {
            this.mForceClassForPath.put(str, Long.class);
            return this;
        }

        public Builder forceDoubleForPath(String str) {
            this.mForceClassForPath.put(str, Double.class);
            return this;
        }

        public Builder forceBooleanForPath(String str) {
            this.mForceClassForPath.put(str, Boolean.class);
            return this;
        }

        public Builder skipTag(String str) {
            this.mSkippedTags.add(str);
            return this;
        }

        public Builder skipAttribute(String str) {
            this.mSkippedAttributes.add(str);
            return this;
        }

        public XmlToJson build() {
            return new XmlToJson(this);
        }
    }

    private XmlToJson(Builder builder) {
        this.mIndentationPattern = DEFAULT_INDENTATION;
        this.mSkippedAttributes = new HashSet<>();
        this.mSkippedTags = new HashSet<>();
        this.mStringSource = builder.mStringSource;
        this.mInputStreamSource = builder.mInputStreamSource;
        this.mInputEncoding = builder.mInputEncoding;
        this.mForceListPaths = builder.mForceListPaths;
        this.mAttributeNameReplacements = builder.mAttributeNameReplacements;
        this.mContentNameReplacements = builder.mContentNameReplacements;
        this.mForceClassForPath = builder.mForceClassForPath;
        this.mSkippedAttributes = builder.mSkippedAttributes;
        this.mSkippedTags = builder.mSkippedTags;
        this.mJsonObject = convertToJSONObject();
    }

    public JSONObject toJson() {
        return this.mJsonObject;
    }

    private JSONObject convertToJSONObject() {
        try {
            Tag tag = new Tag("", "xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(false);
            XmlPullParser newPullParser = newInstance.newPullParser();
            setInput(newPullParser);
            for (int eventType = newPullParser.getEventType(); eventType != 0; eventType = newPullParser.next()) {
            }
            readTags(tag, newPullParser);
            unsetInput();
            return convertTagToJson(tag, false);
        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    private void setInput(XmlPullParser xmlPullParser) {
        StringReader stringReader = this.mStringSource;
        if (stringReader != null) {
            try {
                xmlPullParser.setInput(stringReader);
                return;
            } catch (XmlPullParserException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    return;
                }
                return;
            }
        }
        try {
            xmlPullParser.setInput(this.mInputStreamSource, this.mInputEncoding);
        } catch (XmlPullParserException e2) {
            Log.e(TAG, Utils.getErrorMessage(e2));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
        }
    }

    private void unsetInput() {
        StringReader stringReader = this.mStringSource;
        if (stringReader != null) {
            stringReader.close();
        }
    }

    private void readTags(Tag tag, XmlPullParser xmlPullParser) {
        int next;
        do {
            try {
                next = xmlPullParser.next();
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    String str = tag.getPath() + RemoteSettings.FORWARD_SLASH_STRING + name;
                    boolean contains = this.mSkippedTags.contains(str);
                    Tag tag2 = new Tag(str, name);
                    if (!contains) {
                        tag.addChild(tag2);
                    }
                    int attributeCount = xmlPullParser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++) {
                        String attributeName = xmlPullParser.getAttributeName(i);
                        String attributeValue = xmlPullParser.getAttributeValue(i);
                        String str2 = tag.getPath() + RemoteSettings.FORWARD_SLASH_STRING + tag2.getName() + RemoteSettings.FORWARD_SLASH_STRING + attributeName;
                        if (!this.mSkippedAttributes.contains(str2)) {
                            Tag tag3 = new Tag(str2, getAttributeNameReplacement(str2, attributeName));
                            tag3.setContent(attributeValue);
                            tag2.addChild(tag3);
                        }
                    }
                    readTags(tag2, xmlPullParser);
                } else if (next == 4) {
                    tag.setContent(xmlPullParser.getText());
                } else {
                    if (next == 3 || next == 1) {
                        return;
                    }
                    Log.i(TAG, "unknown xml eventType " + next);
                }
            } catch (IOException | NullPointerException | XmlPullParserException e) {
                Log.e(TAG, Utils.getErrorMessage(e));
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    return;
                }
                return;
            }
        } while (next != 1);
    }

    private JSONObject convertTagToJson(Tag tag, boolean z) {
        JSONObject jSONObject = new JSONObject();
        if (tag.getContent() != null) {
            String path = tag.getPath();
            putContent(path, jSONObject, getContentNameReplacement(path, "content"), tag.getContent());
        }
        try {
            for (ArrayList<Tag> arrayList : tag.getGroupedElements().values()) {
                if (arrayList.size() == 1) {
                    Tag tag2 = arrayList.get(0);
                    if (isForcedList(tag2)) {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(convertTagToJson(tag2, true));
                        jSONObject.put(tag2.getName(), jSONArray);
                    } else if (tag2.hasChildren()) {
                        jSONObject.put(tag2.getName(), convertTagToJson(tag2, false));
                    } else {
                        putContent(tag2.getPath(), jSONObject, tag2.getName(), tag2.getContent());
                    }
                } else {
                    JSONArray jSONArray2 = new JSONArray();
                    Iterator<Tag> it = arrayList.iterator();
                    while (it.hasNext()) {
                        jSONArray2.put(convertTagToJson(it.next(), true));
                    }
                    jSONObject.put(arrayList.get(0).getName(), jSONArray2);
                }
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void putContent(String str, JSONObject jSONObject, String str2, String str3) {
        Class cls;
        try {
            cls = this.mForceClassForPath.get(str);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
            }
        }
        if (cls == null) {
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put(str2, str3);
        } else {
            if (cls == Integer.class) {
                try {
                    jSONObject.put(str2, Integer.valueOf(Integer.parseInt(str3)));
                } catch (NumberFormatException e2) {
                    jSONObject.put(str2, 0);
                    Log.e(TAG, Utils.getErrorMessage(e2));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                    }
                }
            } else if (cls == Long.class) {
                try {
                    jSONObject.put(str2, Long.valueOf(Long.parseLong(str3)));
                } catch (NumberFormatException e3) {
                    jSONObject.put(str2, 0L);
                    Log.e(TAG, Utils.getErrorMessage(e3));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
                    }
                }
            } else if (cls == Double.class) {
                try {
                    jSONObject.put(str2, Double.valueOf(Double.parseDouble(str3)));
                } catch (NumberFormatException e4) {
                    jSONObject.put(str2, DEFAULT_EMPTY_DOUBLE);
                    Log.e(TAG, Utils.getErrorMessage(e4));
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e4);
                    }
                }
            } else if (cls == Boolean.class) {
                if (str3 == null) {
                    jSONObject.put(str2, false);
                } else if (str3.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                    jSONObject.put(str2, true);
                } else if (str3.equalsIgnoreCase("false")) {
                    jSONObject.put(str2, false);
                } else {
                    jSONObject.put(str2, false);
                }
            }
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    private boolean isForcedList(Tag tag) {
        return this.mForceListPaths.contains(tag.getPath());
    }

    private String getAttributeNameReplacement(String str, String str2) {
        String str3 = this.mAttributeNameReplacements.get(str);
        return str3 != null ? str3 : str2;
    }

    private String getContentNameReplacement(String str, String str2) {
        String str3 = this.mContentNameReplacements.get(str);
        return str3 != null ? str3 : str2;
    }

    public String toString() {
        JSONObject jSONObject = this.mJsonObject;
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        return null;
    }

    public String toFormattedString(String str) {
        if (str == null) {
            this.mIndentationPattern = DEFAULT_INDENTATION;
        } else {
            this.mIndentationPattern = str;
        }
        return toFormattedString();
    }

    public String toFormattedString() {
        if (this.mJsonObject == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        format(this.mJsonObject, sb, "");
        sb.append("}\n");
        return sb.toString();
    }

    private void format(JSONObject jSONObject, StringBuilder sb, String str) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            sb.append(str);
            sb.append(this.mIndentationPattern);
            sb.append("\"");
            sb.append(next);
            sb.append("\": ");
            Object opt = jSONObject.opt(next);
            if (opt instanceof JSONObject) {
                sb.append(str);
                sb.append("{\n");
                format((JSONObject) opt, sb, str + this.mIndentationPattern);
                sb.append(str);
                sb.append(this.mIndentationPattern);
                sb.append("}");
            } else if (opt instanceof JSONArray) {
                formatArray((JSONArray) opt, sb, str + this.mIndentationPattern);
            } else {
                formatValue(opt, sb);
            }
            if (keys.hasNext()) {
                sb.append(",\n");
            } else {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
    }

    private void formatArray(JSONArray jSONArray, StringBuilder sb, String str) {
        sb.append("[\n");
        for (int i = 0; i < jSONArray.length(); i++) {
            Object opt = jSONArray.opt(i);
            if (opt instanceof JSONObject) {
                sb.append(str);
                sb.append(this.mIndentationPattern);
                sb.append("{\n");
                format((JSONObject) opt, sb, str + this.mIndentationPattern);
                sb.append(str);
                sb.append(this.mIndentationPattern);
                sb.append("}");
            } else if (opt instanceof JSONArray) {
                formatArray((JSONArray) opt, sb, str + this.mIndentationPattern);
            } else {
                formatValue(opt, sb);
            }
            if (i < jSONArray.length() - 1) {
                sb.append(",");
            }
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        sb.append(str);
        sb.append("]");
    }

    private void formatValue(Object obj, StringBuilder sb) {
        if (obj instanceof String) {
            String replaceAll = ((String) obj).replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", Matcher.quoteReplacement("\\\"")).replaceAll(RemoteSettings.FORWARD_SLASH_STRING, "\\\\/").replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "\\\\n").replaceAll("\t", "\\\\t").replaceAll("\r", "\\\\r");
            sb.append("\"");
            sb.append(replaceAll);
            sb.append("\"");
            return;
        }
        if (obj instanceof Long) {
            sb.append((Long) obj);
            return;
        }
        if (obj instanceof Integer) {
            sb.append((Integer) obj);
            return;
        }
        if (obj instanceof Boolean) {
            sb.append((Boolean) obj);
        } else if (obj instanceof Double) {
            sb.append((Double) obj);
        } else {
            sb.append(obj.toString());
        }
    }
}

package io.flutter.plugins.firebase.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class QueryBuilder {
    private final List<Map<String, Object>> modifiers;
    private Query query;

    public QueryBuilder(DatabaseReference databaseReference, List<Map<String, Object>> list) {
        this.query = databaseReference;
        this.modifiers = list;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0031. Please report as an issue. */
    public Query build() {
        if (this.modifiers.isEmpty()) {
            return this.query;
        }
        for (Map<String, Object> map : this.modifiers) {
            Object obj = map.get("type");
            Objects.requireNonNull(obj);
            String str = (String) obj;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1349119146:
                    if (str.equals(Constants.CURSOR)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1207110587:
                    if (str.equals(Constants.ORDER_BY)) {
                        c = 1;
                        break;
                    }
                    break;
                case 102976443:
                    if (str.equals(Constants.LIMIT)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    cursor(map);
                    break;
                case 1:
                    orderBy(map);
                    break;
                case 2:
                    limit(map);
                    break;
            }
        }
        return this.query;
    }

    private void limit(Map<String, Object> map) {
        Object obj = map.get("name");
        Objects.requireNonNull(obj);
        String str = (String) obj;
        Object obj2 = map.get(Constants.LIMIT);
        Objects.requireNonNull(obj2);
        int intValue = ((Integer) obj2).intValue();
        if (Constants.LIMIT_TO_FIRST.equals(str)) {
            this.query = this.query.limitToFirst(intValue);
        } else if (Constants.LIMIT_TO_LAST.equals(str)) {
            this.query = this.query.limitToLast(intValue);
        }
    }

    private void orderBy(Map<String, Object> map) {
        Object obj = map.get("name");
        Objects.requireNonNull(obj);
        String str = (String) obj;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -626148087:
                if (str.equals("orderByPriority")) {
                    c = 0;
                    break;
                }
                break;
            case 729747418:
                if (str.equals("orderByKey")) {
                    c = 1;
                    break;
                }
                break;
            case 1200288727:
                if (str.equals("orderByChild")) {
                    c = 2;
                    break;
                }
                break;
            case 1217630252:
                if (str.equals("orderByValue")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.query = this.query.orderByPriority();
                return;
            case 1:
                this.query = this.query.orderByKey();
                return;
            case 2:
                Object obj2 = map.get(Constants.PATH);
                Objects.requireNonNull(obj2);
                this.query = this.query.orderByChild((String) obj2);
                return;
            case 3:
                this.query = this.query.orderByValue();
                return;
            default:
                return;
        }
    }

    private void cursor(Map<String, Object> map) {
        Object obj = map.get("name");
        Objects.requireNonNull(obj);
        String str = (String) obj;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1897186251:
                if (str.equals(Constants.START_AT)) {
                    c = 0;
                    break;
                }
                break;
            case -1601257830:
                if (str.equals(Constants.START_AFTER)) {
                    c = 1;
                    break;
                }
                break;
            case 96650862:
                if (str.equals(Constants.END_AT)) {
                    c = 2;
                    break;
                }
                break;
            case 1108304954:
                if (str.equals(Constants.END_BEFORE)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                startAt(map);
                return;
            case 1:
                startAfter(map);
                return;
            case 2:
                endAt(map);
                return;
            case 3:
                endBefore(map);
                return;
            default:
                return;
        }
    }

    private void startAt(Map<String, Object> map) {
        Object obj = map.get("value");
        String str = (String) map.get("key");
        if (obj instanceof Boolean) {
            if (str == null) {
                this.query = this.query.startAt(((Boolean) obj).booleanValue());
                return;
            } else {
                this.query = this.query.startAt(((Boolean) obj).booleanValue(), str);
                return;
            }
        }
        if (obj instanceof Number) {
            if (str == null) {
                this.query = this.query.startAt(((Number) obj).doubleValue());
                return;
            } else {
                this.query = this.query.startAt(((Number) obj).doubleValue(), str);
                return;
            }
        }
        if (str == null) {
            this.query = this.query.startAt((String) obj);
        } else {
            this.query = this.query.startAt((String) obj, str);
        }
    }

    private void startAfter(Map<String, Object> map) {
        Object obj = map.get("value");
        String str = (String) map.get("key");
        if (obj instanceof Boolean) {
            if (str == null) {
                this.query = this.query.startAfter(((Boolean) obj).booleanValue());
                return;
            } else {
                this.query = this.query.startAfter(((Boolean) obj).booleanValue(), str);
                return;
            }
        }
        if (obj instanceof Number) {
            if (str == null) {
                this.query = this.query.startAfter(((Number) obj).doubleValue());
                return;
            } else {
                this.query = this.query.startAfter(((Number) obj).doubleValue(), str);
                return;
            }
        }
        if (str == null) {
            this.query = this.query.startAfter((String) obj);
        } else {
            this.query = this.query.startAfter((String) obj, str);
        }
    }

    private void endAt(Map<String, Object> map) {
        Object obj = map.get("value");
        String str = (String) map.get("key");
        if (obj instanceof Boolean) {
            if (str == null) {
                this.query = this.query.endAt(((Boolean) obj).booleanValue());
                return;
            } else {
                this.query = this.query.endAt(((Boolean) obj).booleanValue(), str);
                return;
            }
        }
        if (obj instanceof Number) {
            if (str == null) {
                this.query = this.query.endAt(((Number) obj).doubleValue());
                return;
            } else {
                this.query = this.query.endAt(((Number) obj).doubleValue(), str);
                return;
            }
        }
        if (str == null) {
            this.query = this.query.endAt((String) obj);
        } else {
            this.query = this.query.endAt((String) obj, str);
        }
    }

    private void endBefore(Map<String, Object> map) {
        Object obj = map.get("value");
        String str = (String) map.get("key");
        if (obj instanceof Boolean) {
            if (str == null) {
                this.query = this.query.endBefore(((Boolean) obj).booleanValue());
                return;
            } else {
                this.query = this.query.endBefore(((Boolean) obj).booleanValue(), str);
                return;
            }
        }
        if (obj instanceof Number) {
            if (str == null) {
                this.query = this.query.endBefore(((Number) obj).doubleValue());
                return;
            } else {
                this.query = this.query.endBefore(((Number) obj).doubleValue(), str);
                return;
            }
        }
        if (str == null) {
            this.query = this.query.endBefore((String) obj);
        } else {
            this.query = this.query.endBefore((String) obj, str);
        }
    }
}

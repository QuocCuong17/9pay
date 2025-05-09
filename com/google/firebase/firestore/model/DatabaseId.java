package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;

/* loaded from: classes4.dex */
public final class DatabaseId implements Comparable<DatabaseId> {
    public static final String DEFAULT_DATABASE_ID = "(default)";
    public static final DatabaseId EMPTY = forDatabase("", "");
    private final String databaseId;
    private final String projectId;

    public static DatabaseId forProject(String str) {
        return forDatabase(str, "(default)");
    }

    public static DatabaseId forDatabase(String str, String str2) {
        return new DatabaseId(str, str2);
    }

    private DatabaseId(String str, String str2) {
        this.projectId = str;
        this.databaseId = str2;
    }

    public static DatabaseId fromName(String str) {
        ResourcePath fromString = ResourcePath.fromString(str);
        Assert.hardAssert(fromString.length() > 3 && fromString.getSegment(0).equals("projects") && fromString.getSegment(2).equals("databases"), "Tried to parse an invalid resource name: %s", fromString);
        return new DatabaseId(fromString.getSegment(1), fromString.getSegment(3));
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getDatabaseId() {
        return this.databaseId;
    }

    public String toString() {
        return "DatabaseId(" + this.projectId + ", " + this.databaseId + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DatabaseId databaseId = (DatabaseId) obj;
        return this.projectId.equals(databaseId.projectId) && this.databaseId.equals(databaseId.databaseId);
    }

    public int hashCode() {
        return (this.projectId.hashCode() * 31) + this.databaseId.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(DatabaseId databaseId) {
        int compareTo = this.projectId.compareTo(databaseId.projectId);
        return compareTo != 0 ? compareTo : this.databaseId.compareTo(databaseId.databaseId);
    }
}

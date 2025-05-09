package org.tensorflow.lite.support.label;

import java.util.Objects;

/* loaded from: classes6.dex */
public final class Category {
    private static final int DEFAULT_INDEX = -1;
    private static final float TOLERANCE = 1.0E-6f;
    private final String displayName;
    private final int index;
    private final String label;
    private final float score;

    public static Category create(String label, String displayName, float score, int index) {
        return new Category(label, displayName, score, index);
    }

    public static Category create(String label, String displayName, float score) {
        return new Category(label, displayName, score, -1);
    }

    public Category(String label, float score) {
        this(label, "", score, -1);
    }

    private Category(String label, String displayName, float score, int index) {
        this.label = label;
        this.displayName = displayName;
        this.score = score;
        this.index = index;
    }

    public String getLabel() {
        return this.label;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public float getScore() {
        return this.score;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return category.getLabel().equals(this.label) && category.getDisplayName().equals(this.displayName) && Math.abs(category.getScore() - this.score) < TOLERANCE && category.getIndex() == this.index;
    }

    public int hashCode() {
        return Objects.hash(this.label, this.displayName, Float.valueOf(this.score), Integer.valueOf(this.index));
    }

    public String toString() {
        return "<Category \"" + this.label + "\" (displayName=" + this.displayName + " score=" + this.score + " index=" + this.index + ")>";
    }
}

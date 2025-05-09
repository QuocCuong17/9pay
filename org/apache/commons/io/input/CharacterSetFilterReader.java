package org.apache.commons.io.input;

import java.io.Reader;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes5.dex */
public class CharacterSetFilterReader extends AbstractCharacterFilterReader {
    private static final Set<Integer> EMPTY_SET = Collections.emptySet();
    private final Set<Integer> skipSet;

    public CharacterSetFilterReader(Reader reader, Set<Integer> set) {
        super(reader);
        this.skipSet = set == null ? EMPTY_SET : Collections.unmodifiableSet(set);
    }

    @Override // org.apache.commons.io.input.AbstractCharacterFilterReader
    protected boolean filter(int i) {
        return this.skipSet.contains(Integer.valueOf(i));
    }
}

package com.google.thirdparty.publicsuffix;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;
import java.util.Deque;

/* loaded from: classes4.dex */
final class TrieParser {
    private static final Joiner DIRECT_JOINER = Joiner.on("");

    TrieParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence... encodedChunks) {
        return parseFullString(DIRECT_JOINER.join(encodedChunks));
    }

    static ImmutableMap<String, PublicSuffixType> parseFullString(String encoded) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = encoded.length();
        int i = 0;
        while (i < length) {
            i += doParseTrieToBuilder(Queues.newArrayDeque(), encoded, i, builder);
        }
        return builder.buildOrThrow();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if (r1 != ',') goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0050, code lost:
    
        if (r2 >= r0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
    
        r2 = r2 + doParseTrieToBuilder(r8, r9, r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
    
        if (r9.charAt(r2) == '?') goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0061, code lost:
    
        if (r9.charAt(r2) != ',') goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int doParseTrieToBuilder(Deque<CharSequence> stack, CharSequence encoded, int start, ImmutableMap.Builder<String, PublicSuffixType> builder) {
        int length = encoded.length();
        char c = 0;
        int i = start;
        while (i < length && (c = encoded.charAt(i)) != '&' && c != '?' && c != '!' && c != ':' && c != ',') {
            i++;
        }
        stack.push(reverse(encoded.subSequence(start, i)));
        if (c == '!' || c == '?' || c == ':' || c == ',') {
            String join = DIRECT_JOINER.join(stack);
            if (join.length() > 0) {
                builder.put(join, PublicSuffixType.fromCode(c));
            }
        }
        int i2 = i + 1;
        if (c != '?') {
        }
        stack.pop();
        return i2 - start;
    }

    private static CharSequence reverse(CharSequence s) {
        return new StringBuilder(s).reverse();
    }
}

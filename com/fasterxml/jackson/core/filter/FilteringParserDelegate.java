package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes2.dex */
public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;
    protected TokenFilter.Inclusion _inclusion;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    @Deprecated
    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        this(jsonParser, tokenFilter, z ? TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH : TokenFilter.Inclusion.ONLY_INCLUDE_ALL, z2);
    }

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, TokenFilter.Inclusion inclusion, boolean z) {
        super(jsonParser);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._inclusion = inclusion;
        this._allowMultipleMatches = z;
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    @Deprecated
    public final int getCurrentTokenId() {
        return currentTokenId();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i == 0 : jsonToken.id() == i;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            JsonStreamContext parent = _filterContext.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getCurrentName();
        }
        return _filterContext.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String currentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            JsonStreamContext parent = _filterContext.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getCurrentName();
        }
        return _filterContext.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        throw new UnsupportedOperationException("Can not currently override name during filtering read");
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0126, code lost:
    
        if (r1 != null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0128, code lost:
    
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x012f, code lost:
    
        r1 = r6._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0135, code lost:
    
        if (r1 != null) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0137, code lost:
    
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0140, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0142, code lost:
    
        r1 = r1.filterStartArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0146, code lost:
    
        r6._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x014a, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x014c, code lost:
    
        r6._headContext = r6._headContext.createChildArrayContext(r1, true);
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0156, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0157, code lost:
    
        if (r1 == null) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x015d, code lost:
    
        if (r6._inclusion != com.fasterxml.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x015f, code lost:
    
        r6._headContext = r6._headContext.createChildArrayContext(r1, true);
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0169, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x016a, code lost:
    
        r6._headContext = r6._headContext.createChildArrayContext(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0176, code lost:
    
        if (r6._inclusion != com.fasterxml.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0178, code lost:
    
        r0 = _nextTokenWithBuffering(r6._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x017e, code lost:
    
        if (r0 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0180, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0182, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0183, code lost:
    
        r1 = r6._headContext.isStartHandled();
        r2 = r6._headContext.getFilter();
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x018f, code lost:
    
        if (r2 == null) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0193, code lost:
    
        if (r2 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0195, code lost:
    
        r2.filterFinishArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0198, code lost:
    
        r2 = r6._headContext.getParent();
        r6._headContext = r2;
        r6._itemFilter = r2.getFilter();
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x01a6, code lost:
    
        if (r1 == false) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01a8, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01aa, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01ab, code lost:
    
        r1 = r6._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x01af, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01b1, code lost:
    
        r6._headContext = r6._headContext.createChildObjectContext(r1, true);
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01bb, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01bc, code lost:
    
        if (r1 != null) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01be, code lost:
    
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01c4, code lost:
    
        r1 = r6._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01ca, code lost:
    
        if (r1 != null) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01cc, code lost:
    
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x01d4, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01d6, code lost:
    
        r1 = r1.filterStartObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01da, code lost:
    
        r6._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x01de, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x01e0, code lost:
    
        r6._headContext = r6._headContext.createChildObjectContext(r1, true);
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x01ea, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01eb, code lost:
    
        if (r1 == null) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x01f1, code lost:
    
        if (r6._inclusion != com.fasterxml.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_NON_NULL) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x01f3, code lost:
    
        r6._headContext = r6._headContext.createChildObjectContext(r1, true);
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x01fd, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x01fe, code lost:
    
        r6._headContext = r6._headContext.createChildObjectContext(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x020a, code lost:
    
        if (r6._inclusion != com.fasterxml.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x020c, code lost:
    
        r0 = _nextTokenWithBuffering(r6._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0212, code lost:
    
        if (r0 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0214, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0216, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
    
        if (r0 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002e, code lost:
    
        r2 = r0.nextTokenToRead();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0032, code lost:
    
        if (r2 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0037, code lost:
    
        r2 = r6._headContext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0039, code lost:
    
        if (r0 != r2) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0059, code lost:
    
        r0 = r2.findChildOf(r0);
        r6._exposedContext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005f, code lost:
    
        if (r0 == null) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0068, code lost:
    
        throw _constructError("Unexpected problem: chain of filtered context broken");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x003b, code lost:
    
        r6._exposedContext = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0041, code lost:
    
        if (r0.inArray() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0043, code lost:
    
        r0 = r6.delegate.getCurrentToken();
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004c, code lost:
    
        r0 = r6.delegate.currentToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0054, code lost:
    
        if (r0 == com.fasterxml.jackson.core.JsonToken.FIELD_NAME) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0056, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0058, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0034, code lost:
    
        r6._currToken = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0036, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0069, code lost:
    
        r0 = r6.delegate.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x006f, code lost:
    
        if (r0 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0071, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0073, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0074, code lost:
    
        r1 = r0.id();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x007a, code lost:
    
        if (r1 == 1) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x007d, code lost:
    
        if (r1 == 2) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0080, code lost:
    
        if (r1 == 3) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0083, code lost:
    
        if (r1 == 4) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0086, code lost:
    
        if (r1 == 5) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0088, code lost:
    
        r1 = r6._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x008c, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x008e, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0090, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0091, code lost:
    
        if (r1 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0093, code lost:
    
        r1 = r6._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x009b, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x009d, code lost:
    
        if (r1 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00a5, code lost:
    
        if (r1.includeValue(r6.delegate) == false) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00ab, code lost:
    
        if (_verifyAllowedMatches() == false) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00ad, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00af, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x021b, code lost:
    
        return _nextToken2();
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00b0, code lost:
    
        r1 = r6.delegate.getCurrentName();
        r2 = r6._headContext.setFieldName(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00be, code lost:
    
        if (r2 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c0, code lost:
    
        r6._itemFilter = r2;
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c4, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c5, code lost:
    
        if (r2 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c7, code lost:
    
        r6.delegate.nextToken();
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00d3, code lost:
    
        r1 = r2.includeProperty(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00d7, code lost:
    
        if (r1 != null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00d9, code lost:
    
        r6.delegate.nextToken();
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00e5, code lost:
    
        r6._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00e9, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00ef, code lost:
    
        if (_verifyAllowedMatches() == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00f5, code lost:
    
        if (r6._inclusion != com.fasterxml.jackson.core.filter.TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00f7, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00f9, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00fa, code lost:
    
        r6.delegate.nextToken();
        r6.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0108, code lost:
    
        if (r6._inclusion == com.fasterxml.jackson.core.filter.TokenFilter.Inclusion.ONLY_INCLUDE_ALL) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x010a, code lost:
    
        r0 = _nextTokenWithBuffering(r6._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0110, code lost:
    
        if (r0 == null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0112, code lost:
    
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0114, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0115, code lost:
    
        r1 = r6._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0119, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x011b, code lost:
    
        r6._headContext = r6._headContext.createChildArrayContext(r1, true);
        r6._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0125, code lost:
    
        return r0;
     */
    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JsonToken nextToken() throws IOException {
        JsonToken jsonToken;
        if (!this._allowMultipleMatches && (jsonToken = this._currToken) != null && this._exposedContext == null && jsonToken.isScalarValue() && !this._headContext.isStartHandled() && this._inclusion == TokenFilter.Inclusion.ONLY_INCLUDE_ALL && this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._currToken = null;
            return null;
        }
        TokenFilterContext tokenFilterContext = this._exposedContext;
    }

    protected final JsonToken _nextToken2() throws IOException {
        TokenFilter checkValue;
        JsonToken _nextTokenWithBuffering;
        JsonToken _nextTokenWithBuffering2;
        JsonToken _nextTokenWithBuffering3;
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                this._currToken = nextToken;
                return nextToken;
            }
            int id2 = nextToken.id();
            if (id2 == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    this._currToken = nextToken;
                    return nextToken;
                }
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                    if (checkValue2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue2 != TokenFilter.INCLUDE_ALL) {
                            checkValue2 = checkValue2.filterStartObject();
                        }
                        this._itemFilter = checkValue2;
                        if (checkValue2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        if (checkValue2 != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        this._headContext = this._headContext.createChildObjectContext(checkValue2, false);
                        if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH && (_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext)) != null) {
                            this._currToken = _nextTokenWithBuffering3;
                            return _nextTokenWithBuffering3;
                        }
                    }
                }
            } else {
                if (id2 != 2) {
                    if (id2 == 3) {
                        TokenFilter tokenFilter2 = this._itemFilter;
                        if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        if (tokenFilter2 == null) {
                            this.delegate.skipChildren();
                        } else {
                            TokenFilter checkValue3 = this._headContext.checkValue(tokenFilter2);
                            if (checkValue3 == null) {
                                this.delegate.skipChildren();
                            } else {
                                if (checkValue3 != TokenFilter.INCLUDE_ALL) {
                                    checkValue3 = checkValue3.filterStartArray();
                                }
                                this._itemFilter = checkValue3;
                                if (checkValue3 == TokenFilter.INCLUDE_ALL) {
                                    this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                    this._currToken = nextToken;
                                    return nextToken;
                                }
                                if (checkValue3 != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                                    this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                    this._currToken = nextToken;
                                    return nextToken;
                                }
                                this._headContext = this._headContext.createChildArrayContext(checkValue3, false);
                                if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH && (_nextTokenWithBuffering2 = _nextTokenWithBuffering(this._headContext)) != null) {
                                    this._currToken = _nextTokenWithBuffering2;
                                    return _nextTokenWithBuffering2;
                                }
                            }
                        }
                    } else if (id2 != 4) {
                        if (id2 == 5) {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            if (fieldName == TokenFilter.INCLUDE_ALL) {
                                this._itemFilter = fieldName;
                                this._currToken = nextToken;
                                return nextToken;
                            }
                            if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                TokenFilter includeProperty = fieldName.includeProperty(currentName);
                                if (includeProperty == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = includeProperty;
                                    if (includeProperty == TokenFilter.INCLUDE_ALL) {
                                        if (_verifyAllowedMatches()) {
                                            if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
                                                this._currToken = nextToken;
                                                return nextToken;
                                            }
                                        } else {
                                            this.delegate.nextToken();
                                            this.delegate.skipChildren();
                                        }
                                    } else if (this._inclusion != TokenFilter.Inclusion.ONLY_INCLUDE_ALL && (_nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext)) != null) {
                                        this._currToken = _nextTokenWithBuffering;
                                        return _nextTokenWithBuffering;
                                    }
                                }
                            }
                        } else {
                            TokenFilter tokenFilter3 = this._itemFilter;
                            if (tokenFilter3 == TokenFilter.INCLUDE_ALL) {
                                this._currToken = nextToken;
                                return nextToken;
                            }
                            if (tokenFilter3 != null && ((checkValue = this._headContext.checkValue(tokenFilter3)) == TokenFilter.INCLUDE_ALL || (checkValue != null && checkValue.includeValue(this.delegate)))) {
                                if (_verifyAllowedMatches()) {
                                    this._currToken = nextToken;
                                    return nextToken;
                                }
                            }
                        }
                    }
                }
                boolean isStartHandled = this._headContext.isStartHandled();
                TokenFilter filter = this._headContext.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                TokenFilterContext parent = this._headContext.getParent();
                this._headContext = parent;
                this._itemFilter = parent.getFilter();
                if (isStartHandled) {
                    this._currToken = nextToken;
                    return nextToken;
                }
            }
        }
    }

    protected final JsonToken _nextTokenWithBuffering(TokenFilterContext tokenFilterContext) throws IOException {
        TokenFilter checkValue;
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                return nextToken;
            }
            int id2 = nextToken.id();
            boolean z = false;
            if (id2 == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    return nextToken;
                }
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                    if (checkValue2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue2 != TokenFilter.INCLUDE_ALL) {
                            checkValue2 = checkValue2.filterStartObject();
                        }
                        this._itemFilter = checkValue2;
                        if (checkValue2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            return _nextBuffered(tokenFilterContext);
                        }
                        if (checkValue2 != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            this._headContext = this._headContext.createChildArrayContext(checkValue2, true);
                            return _nextBuffered(tokenFilterContext);
                        }
                        this._headContext = this._headContext.createChildObjectContext(checkValue2, false);
                    }
                }
            } else {
                if (id2 != 2) {
                    if (id2 == 3) {
                        TokenFilter checkValue3 = this._headContext.checkValue(this._itemFilter);
                        if (checkValue3 == null) {
                            this.delegate.skipChildren();
                        } else {
                            if (checkValue3 != TokenFilter.INCLUDE_ALL) {
                                checkValue3 = checkValue3.filterStartArray();
                            }
                            this._itemFilter = checkValue3;
                            if (checkValue3 == TokenFilter.INCLUDE_ALL) {
                                this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (checkValue3 != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                                this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                return _nextBuffered(tokenFilterContext);
                            }
                            this._headContext = this._headContext.createChildArrayContext(checkValue3, false);
                        }
                    } else if (id2 != 4) {
                        if (id2 == 5) {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            if (fieldName == TokenFilter.INCLUDE_ALL) {
                                this._itemFilter = fieldName;
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                TokenFilter includeProperty = fieldName.includeProperty(currentName);
                                if (includeProperty == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = includeProperty;
                                    if (includeProperty != TokenFilter.INCLUDE_ALL) {
                                        continue;
                                    } else {
                                        if (_verifyAllowedMatches()) {
                                            return _nextBuffered(tokenFilterContext);
                                        }
                                        this._itemFilter = this._headContext.setFieldName(currentName);
                                    }
                                }
                            }
                        } else {
                            TokenFilter tokenFilter2 = this._itemFilter;
                            if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (tokenFilter2 != null && ((checkValue = this._headContext.checkValue(tokenFilter2)) == TokenFilter.INCLUDE_ALL || (checkValue != null && checkValue.includeValue(this.delegate)))) {
                                if (_verifyAllowedMatches()) {
                                    return _nextBuffered(tokenFilterContext);
                                }
                            }
                        }
                    }
                }
                TokenFilter filter = this._headContext.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                TokenFilterContext tokenFilterContext2 = this._headContext;
                if ((tokenFilterContext2 == tokenFilterContext) && tokenFilterContext2.isStartHandled()) {
                    z = true;
                }
                TokenFilterContext parent = this._headContext.getParent();
                this._headContext = parent;
                this._itemFilter = parent.getFilter();
                if (z) {
                    return nextToken;
                }
            }
        }
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead != null) {
            return nextTokenToRead;
        }
        while (tokenFilterContext != this._headContext) {
            tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
            this._exposedContext = tokenFilterContext;
            if (tokenFilterContext == null) {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken nextTokenToRead2 = tokenFilterContext.nextTokenToRead();
            if (nextTokenToRead2 != null) {
                return nextTokenToRead2;
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    private final boolean _verifyAllowedMatches() throws IOException {
        int i = this._matchCount;
        if (i != 0 && !this._allowMultipleMatches) {
            return false;
        }
        this._matchCount = i + 1;
        return true;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() throws IOException {
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                return this;
            }
            if (nextToken.isStructStart()) {
                i++;
            } else if (nextToken.isStructEnd() && i - 1 == 0) {
                return this;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName();
        }
        return this.delegate.getText();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return false;
        }
        return this.delegate.hasTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName().toCharArray();
        }
        return this.delegate.getTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName().length();
        }
        return this.delegate.getTextLength();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return 0;
        }
        return this.delegate.getTextOffset();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        return this.delegate.getValueAsInt(i);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException {
        return this.delegate.getValueAsLong(j);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d) throws IOException {
        return this.delegate.getValueAsDouble(d);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) throws IOException {
        return this.delegate.getValueAsBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName();
        }
        return this.delegate.getValueAsString();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName();
        }
        return this.delegate.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    protected JsonStreamContext _filterContext() {
        TokenFilterContext tokenFilterContext = this._exposedContext;
        return tokenFilterContext != null ? tokenFilterContext : this._headContext;
    }
}

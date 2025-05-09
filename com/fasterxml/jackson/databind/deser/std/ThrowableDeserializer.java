package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

/* loaded from: classes3.dex */
public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";
    protected static final String PROP_NAME_SUPPRESSED = "suppressed";
    private static final long serialVersionUID = 1;

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, NameTransformer nameTransformer) {
        super(beanDeserializer, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, nameTransformer);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d5  */
    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.deser.BeanDeserializerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._beanType.isAbstract()) {
            return deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), jsonParser, "abstract type (need to add/enable type information?)", new Object[0]);
        }
        boolean canCreateFromString = this._valueInstantiator.canCreateFromString();
        boolean canCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
        if (!canCreateFromString && !canCreateUsingDefault) {
            return deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), jsonParser, "Throwable needs a default constructor, a single-String-arg constructor; or explicit @JsonCreator", new Object[0]);
        }
        int i = 0;
        Throwable th = null;
        Object[] objArr = null;
        Throwable[] thArr = null;
        while (!jsonParser.hasToken(JsonToken.END_OBJECT)) {
            String currentName = jsonParser.currentName();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (find != null) {
                if (th != null) {
                    find.deserializeAndSet(jsonParser, deserializationContext, th);
                } else {
                    if (objArr == null) {
                        int size = this._beanProperties.size();
                        objArr = new Object[size + size];
                    }
                    int i2 = i + 1;
                    objArr[i] = find;
                    i = i2 + 1;
                    objArr[i2] = find.deserialize(jsonParser, deserializationContext);
                }
            } else if (!"message".equals(currentName)) {
                if (PROP_NAME_SUPPRESSED.equals(currentName)) {
                    thArr = (Throwable[]) deserializationContext.readValue(jsonParser, Throwable[].class);
                }
                if (this._ignorableProps == null) {
                }
                if (this._anySetter == null) {
                }
            } else {
                if (canCreateFromString) {
                    th = (Throwable) this._valueInstantiator.createFromString(deserializationContext, jsonParser.getValueAsString());
                }
                if (this._ignorableProps == null && this._ignorableProps.contains(currentName)) {
                    jsonParser.skipChildren();
                } else if (this._anySetter == null) {
                    this._anySetter.deserializeAndSet(jsonParser, deserializationContext, th, currentName);
                } else {
                    handleUnknownProperty(jsonParser, deserializationContext, th, currentName);
                }
            }
            jsonParser.nextToken();
        }
        if (th == null) {
            if (canCreateFromString) {
                th = (Throwable) this._valueInstantiator.createFromString(deserializationContext, null);
            } else {
                th = (Throwable) this._valueInstantiator.createUsingDefault(deserializationContext);
            }
        }
        if (objArr != null) {
            for (int i3 = 0; i3 < i; i3 += 2) {
                ((SettableBeanProperty) objArr[i3]).set(th, objArr[i3 + 1]);
            }
        }
        if (thArr != null) {
            for (Throwable th2 : thArr) {
                th.addSuppressed(th2);
            }
        }
        return th;
    }
}

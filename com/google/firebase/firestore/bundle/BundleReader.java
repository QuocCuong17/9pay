package com.google.firebase.firestore.bundle;

import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import com.google.firebase.firestore.util.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class BundleReader {
    protected static final int BUFFER_CAPACITY = 1024;
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private ByteBuffer buffer;
    private final InputStream bundleInputStream;
    long bytesRead;
    private final InputStreamReader dataReader;
    BundleMetadata metadata;
    private final BundleSerializer serializer;

    public BundleReader(BundleSerializer bundleSerializer, InputStream inputStream) {
        this.serializer = bundleSerializer;
        this.bundleInputStream = inputStream;
        this.dataReader = new InputStreamReader(inputStream);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        this.buffer = allocate;
        allocate.flip();
    }

    public BundleMetadata getBundleMetadata() throws IOException, JSONException {
        BundleMetadata bundleMetadata = this.metadata;
        if (bundleMetadata != null) {
            return bundleMetadata;
        }
        BundleElement readNextElement = readNextElement();
        if (!(readNextElement instanceof BundleMetadata)) {
            throw abort("Expected first element in bundle to be a metadata object");
        }
        BundleMetadata bundleMetadata2 = (BundleMetadata) readNextElement;
        this.metadata = bundleMetadata2;
        this.bytesRead = 0L;
        return bundleMetadata2;
    }

    public BundleElement getNextElement() throws IOException, JSONException {
        getBundleMetadata();
        return readNextElement();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public void close() throws IOException {
        this.bundleInputStream.close();
    }

    private BundleElement readNextElement() throws IOException, JSONException {
        String readLengthPrefix = readLengthPrefix();
        if (readLengthPrefix == null) {
            return null;
        }
        String readJsonString = readJsonString(Integer.parseInt(readLengthPrefix));
        this.bytesRead += readLengthPrefix.getBytes(UTF8_CHARSET).length + r1;
        return decodeBundleElement(readJsonString);
    }

    private String readLengthPrefix() throws IOException {
        int indexOfOpenBracket;
        do {
            indexOfOpenBracket = indexOfOpenBracket();
            if (indexOfOpenBracket != -1) {
                break;
            }
        } while (pullMoreData());
        if (this.buffer.remaining() == 0) {
            return null;
        }
        if (indexOfOpenBracket == -1) {
            throw abort("Reached the end of bundle when a length string is expected.");
        }
        byte[] bArr = new byte[indexOfOpenBracket];
        this.buffer.get(bArr);
        return UTF8_CHARSET.decode(ByteBuffer.wrap(bArr)).toString();
    }

    private int indexOfOpenBracket() {
        this.buffer.mark();
        int i = 0;
        while (true) {
            try {
                if (i >= this.buffer.remaining()) {
                    i = -1;
                    break;
                }
                if (this.buffer.get() == 123) {
                    break;
                }
                i++;
            } finally {
                this.buffer.reset();
            }
        }
        return i;
    }

    private String readJsonString(int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            if (this.buffer.remaining() == 0 && !pullMoreData()) {
                throw abort("Reached the end of bundle when more data was expected.");
            }
            int min = Math.min(i, this.buffer.remaining());
            byteArrayOutputStream.write(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), min);
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + min);
            i -= min;
        }
        return byteArrayOutputStream.toString(UTF8_CHARSET.name());
    }

    private boolean pullMoreData() throws IOException {
        this.buffer.compact();
        int read = this.bundleInputStream.read(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), this.buffer.remaining());
        boolean z = read > 0;
        if (z) {
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + read);
        }
        this.buffer.flip();
        return z;
    }

    private BundleElement decodeBundleElement(String str) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has(TtmlNode.TAG_METADATA)) {
            BundleMetadata decodeBundleMetadata = this.serializer.decodeBundleMetadata(jSONObject.getJSONObject(TtmlNode.TAG_METADATA));
            Logger.debug("BundleElement", "BundleMetadata element loaded", new Object[0]);
            return decodeBundleMetadata;
        }
        if (jSONObject.has("namedQuery")) {
            NamedQuery decodeNamedQuery = this.serializer.decodeNamedQuery(jSONObject.getJSONObject("namedQuery"));
            Logger.debug("BundleElement", "Query loaded: " + decodeNamedQuery.getName(), new Object[0]);
            return decodeNamedQuery;
        }
        if (jSONObject.has("documentMetadata")) {
            BundledDocumentMetadata decodeBundledDocumentMetadata = this.serializer.decodeBundledDocumentMetadata(jSONObject.getJSONObject("documentMetadata"));
            Logger.debug("BundleElement", "Document metadata loaded: " + decodeBundledDocumentMetadata.getKey(), new Object[0]);
            return decodeBundledDocumentMetadata;
        }
        if (jSONObject.has(WorkflowModule.TYPE_DOCUMENT)) {
            BundleDocument decodeDocument = this.serializer.decodeDocument(jSONObject.getJSONObject(WorkflowModule.TYPE_DOCUMENT));
            Logger.debug("BundleElement", "Document loaded: " + decodeDocument.getKey(), new Object[0]);
            return decodeDocument;
        }
        throw abort("Cannot decode unknown Bundle element: " + str);
    }

    private IllegalArgumentException abort(String str) throws IOException {
        close();
        throw new IllegalArgumentException("Invalid bundle: " + str);
    }
}

package co.hyperverge.hypersnapsdk.analytics.mixpanel.network;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.squareup.tape2.ObjectQueue;
import com.squareup.tape2.QueueFile;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: classes2.dex */
public class EventSyncRepo {
    private static final String EVENT_SYNC_FILENAME = "events_queue";
    private static final String EVENT_SYNC_FOLDER = "events";
    private static final String TAG = "co.hyperverge.hypersnapsdk.analytics.mixpanel.network.EventSyncRepo";
    private ObjectQueue<MixPanelEvent> eventQueue;

    public EventSyncRepo(Context context) throws IOException {
        this.eventQueue = null;
        try {
            File syncFile = getSyncFile(context);
            if (syncFile != null) {
                this.eventQueue = ObjectQueue.create(new QueueFile.Builder(syncFile).build(), new EventConverter(new Gson()));
            }
        } catch (IOException e) {
            Log.e(TAG, "EventSyncHelper: ", e);
        }
        if (this.eventQueue == null) {
            throw new IOException("Error in creating Events Queue: eventQueue is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPendingEvents() {
        return !this.eventQueue.isEmpty();
    }

    Iterator<MixPanelEvent> getEvents() {
        Log.v(TAG, "getEvents() called");
        return this.eventQueue.iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEventsCount() {
        return this.eventQueue.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<MixPanelEvent> getEventsList() throws IOException {
        Log.v(TAG, "peekEvents() called when count = [" + this.eventQueue.size() + "]");
        Math.min(50, this.eventQueue.size());
        return this.eventQueue.asList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeEvents(int i) throws IOException {
        Log.v(TAG, "removeEvents() called with: count = [" + i + "]");
        this.eventQueue.remove(Math.min(i, this.eventQueue.size()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addEvent(MixPanelEvent mixPanelEvent) {
        Log.v(TAG, "addEvent() called with: event = [" + mixPanelEvent + "]");
        try {
            this.eventQueue.add(mixPanelEvent);
            return true;
        } catch (IOException e) {
            Log.e(TAG, "add: ", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addEvents(List<MixPanelEvent> list) {
        Log.v(TAG, "addEvents() called with: events = [" + list + "]");
        try {
            Iterator<MixPanelEvent> it = list.iterator();
            while (it.hasNext()) {
                this.eventQueue.add(it.next());
            }
            return true;
        } catch (IOException e) {
            Log.e(TAG, "addEvents: ", e);
            return false;
        }
    }

    MixPanelEvent peek() {
        try {
            return this.eventQueue.peek();
        } catch (IOException e) {
            Log.e(TAG, "peek: ", e);
            return null;
        }
    }

    private File getSyncFile(Context context) {
        File file = new File(context.getFilesDir(), EVENT_SYNC_FOLDER);
        if (!file.exists() && !file.mkdirs()) {
            Log.e(TAG, "getSyncFile: ", new IllegalStateException("Sync dir: " + file.getPath() + " could not be created"));
            return null;
        }
        return new File(file, EVENT_SYNC_FILENAME);
    }

    /* loaded from: classes2.dex */
    public static class EventConverter implements ObjectQueue.Converter<MixPanelEvent> {
        private final Gson gson;

        public EventConverter(Gson gson) {
            this.gson = gson;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.tape2.ObjectQueue.Converter
        public MixPanelEvent from(byte[] bArr) {
            return (MixPanelEvent) this.gson.fromJson((Reader) new InputStreamReader(new ByteArrayInputStream(bArr)), MixPanelEvent.class);
        }

        @Override // com.squareup.tape2.ObjectQueue.Converter
        public void toStream(MixPanelEvent mixPanelEvent, OutputStream outputStream) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            this.gson.toJson(mixPanelEvent, outputStreamWriter);
            outputStreamWriter.close();
        }
    }
}

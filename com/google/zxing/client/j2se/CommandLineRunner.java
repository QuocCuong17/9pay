package com.google.zxing.client.j2se;

import com.beust.jcommander.JCommander;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public final class CommandLineRunner {
    private CommandLineRunner() {
    }

    public static void main(String[] strArr) throws Exception {
        URI uri;
        DecoderConfig decoderConfig = new DecoderConfig();
        JCommander jCommander = new JCommander(decoderConfig, strArr);
        jCommander.setProgramName("CommandLineRunner");
        if (decoderConfig.help) {
            jCommander.usage();
            return;
        }
        List arrayList = new ArrayList(decoderConfig.inputPaths.size());
        Iterator<String> it = decoderConfig.inputPaths.iterator();
        while (true) {
            int i = 0;
            if (it.hasNext()) {
                String next = it.next();
                try {
                    uri = new URI(next);
                } catch (URISyntaxException e) {
                    if (!Files.exists(Paths.get(next, new String[0]), new LinkOption[0])) {
                        throw e;
                    }
                    uri = new URI("file", next, null);
                }
                arrayList.add(uri);
            } else {
                do {
                    arrayList = retainValid(expand(arrayList), decoderConfig.recursive);
                    if (!decoderConfig.recursive) {
                        break;
                    }
                } while (isExpandable(arrayList));
                int size = arrayList.size();
                if (size == 0) {
                    jCommander.usage();
                    return;
                }
                ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue(arrayList);
                int min = Math.min(size, Runtime.getRuntime().availableProcessors());
                if (min > 1) {
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(min);
                    ArrayList arrayList2 = new ArrayList(min);
                    for (int i2 = 0; i2 < min; i2++) {
                        arrayList2.add(newFixedThreadPool.submit(new DecodeWorker(decoderConfig, concurrentLinkedQueue)));
                    }
                    newFixedThreadPool.shutdown();
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        i += ((Integer) ((Future) it2.next()).get()).intValue();
                    }
                } else {
                    i = 0 + new DecodeWorker(decoderConfig, concurrentLinkedQueue).call().intValue();
                }
                if (decoderConfig.brief || size <= 1) {
                    return;
                }
                System.out.println("\nDecoded " + i + " files out of " + size + " successfully (" + ((i * 100) / size) + "%)\n");
                return;
            }
        }
    }

    private static List<URI> expand(List<URI> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (URI uri : list) {
            if (isFileOrDir(uri)) {
                Path path = Paths.get(uri);
                if (Files.isDirectory(path, new LinkOption[0])) {
                    DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path);
                    try {
                        Iterator<Path> it = newDirectoryStream.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().toUri());
                        }
                        if (newDirectoryStream != null) {
                            newDirectoryStream.close();
                        }
                    } finally {
                    }
                } else {
                    arrayList.add(uri);
                }
            } else {
                arrayList.add(uri);
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            URI uri2 = (URI) arrayList.get(i);
            if (uri2.getScheme() == null) {
                arrayList.set(i, Paths.get(uri2.getRawPath(), new String[0]).toUri());
            }
        }
        return arrayList;
    }

    private static List<URI> retainValid(List<URI> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (URI uri : list) {
            boolean z2 = true;
            if (isFileOrDir(uri)) {
                Path path = Paths.get(uri);
                if (path.getFileName().toString().startsWith(".") || (!z && Files.isDirectory(path, new LinkOption[0]))) {
                    z2 = false;
                }
            }
            if (z2) {
                arrayList.add(uri);
            }
        }
        return arrayList;
    }

    private static boolean isExpandable(List<URI> list) {
        for (URI uri : list) {
            if (isFileOrDir(uri) && Files.isDirectory(Paths.get(uri), new LinkOption[0])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFileOrDir(URI uri) {
        return "file".equals(uri.getScheme());
    }
}

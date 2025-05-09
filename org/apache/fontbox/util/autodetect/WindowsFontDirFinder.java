package org.apache.fontbox.util.autodetect;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WindowsFontDirFinder implements FontDirFinder {
    private String getWinDir(String str) throws IOException {
        Process exec;
        Runtime runtime = Runtime.getRuntime();
        if (str.startsWith("Windows 9")) {
            exec = runtime.exec("command.com /c echo %windir%");
        } else {
            exec = runtime.exec("cmd.exe /c echo %windir%");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String readLine = bufferedReader.readLine();
        bufferedReader.close();
        return readLine;
    }

    @Override // org.apache.fontbox.util.autodetect.FontDirFinder
    public List<File> find() {
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            str = System.getProperty("env.windir");
        } catch (SecurityException unused) {
            str = null;
        }
        String property = System.getProperty("os.name");
        if (str == null) {
            try {
                str = getWinDir(property);
            } catch (IOException unused2) {
            }
        }
        if (str != null) {
            if (str.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                str = str.substring(0, str.length() - 1);
            }
            File file = new File(str + File.separator + "FONTS");
            if (file.exists() && file.canRead()) {
                arrayList.add(file);
            }
            File file2 = new File(str.substring(0, 2) + File.separator + "PSFONTS");
            if (file2.exists() && file2.canRead()) {
                arrayList.add(file2);
            }
        } else {
            String str2 = property.endsWith("NT") ? "WINNT" : "WINDOWS";
            char c = 'C';
            char c2 = 'C';
            while (true) {
                if (c2 > 'E') {
                    break;
                }
                File file3 = new File(c2 + ":" + File.separator + str2 + File.separator + "FONTS");
                if (file3.exists() && file3.canRead()) {
                    arrayList.add(file3);
                    break;
                }
                c2 = (char) (c2 + 1);
            }
            while (true) {
                if (c > 'E') {
                    break;
                }
                File file4 = new File(c + ":" + File.separator + "PSFONTS");
                if (file4.exists() && file4.canRead()) {
                    arrayList.add(file4);
                    break;
                }
                c = (char) (c + 1);
            }
        }
        return arrayList;
    }
}

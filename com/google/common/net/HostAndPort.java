package com.google.common.net;

import com.google.common.base.CharMatcher;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@Immutable
@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public final class HostAndPort implements Serializable {
    private static final int NO_PORT = -1;
    private static final long serialVersionUID = 0;
    private final boolean hasBracketlessColons;
    private final String host;
    private final int port;

    private static boolean isValidPort(int port) {
        return port >= 0 && port <= 65535;
    }

    private HostAndPort(String host, int port, boolean hasBracketlessColons) {
        this.host = host;
        this.port = port;
        this.hasBracketlessColons = hasBracketlessColons;
    }

    public String getHost() {
        return this.host;
    }

    public boolean hasPort() {
        return this.port >= 0;
    }

    public int getPort() {
        Preconditions.checkState(hasPort());
        return this.port;
    }

    public int getPortOrDefault(int defaultPort) {
        return hasPort() ? this.port : defaultPort;
    }

    public static HostAndPort fromParts(String host, int port) {
        Preconditions.checkArgument(isValidPort(port), "Port out of range: %s", port);
        HostAndPort fromString = fromString(host);
        Preconditions.checkArgument(!fromString.hasPort(), "Host has a port: %s", host);
        return new HostAndPort(fromString.host, port, fromString.hasBracketlessColons);
    }

    public static HostAndPort fromHost(String host) {
        HostAndPort fromString = fromString(host);
        Preconditions.checkArgument(!fromString.hasPort(), "Host has a port: %s", host);
        return fromString;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HostAndPort fromString(String hostPortString) {
        String str;
        boolean z;
        String str2;
        String substring;
        Preconditions.checkNotNull(hostPortString);
        int i = -1;
        if (hostPortString.startsWith("[")) {
            String[] hostAndPortFromBracketedHost = getHostAndPortFromBracketedHost(hostPortString);
            substring = hostAndPortFromBracketedHost[0];
            str2 = hostAndPortFromBracketedHost[1];
        } else {
            int indexOf = hostPortString.indexOf(58);
            if (indexOf >= 0) {
                int i2 = indexOf + 1;
                if (hostPortString.indexOf(58, i2) == -1) {
                    substring = hostPortString.substring(0, indexOf);
                    str2 = hostPortString.substring(i2);
                }
            }
            str = hostPortString;
            z = indexOf >= 0;
            str2 = null;
            if (!Strings.isNullOrEmpty(str2)) {
                Preconditions.checkArgument(!str2.startsWith("+") && CharMatcher.ascii().matchesAllOf(str2), "Unparseable port number: %s", hostPortString);
                try {
                    i = Integer.parseInt(str2);
                    Preconditions.checkArgument(isValidPort(i), "Port number out of range: %s", hostPortString);
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Unparseable port number: " + hostPortString);
                }
            }
            return new HostAndPort(str, i, z);
        }
        str = substring;
        z = false;
        if (!Strings.isNullOrEmpty(str2)) {
        }
        return new HostAndPort(str, i, z);
    }

    private static String[] getHostAndPortFromBracketedHost(String hostPortString) {
        Preconditions.checkArgument(hostPortString.charAt(0) == '[', "Bracketed host-port string must start with a bracket: %s", hostPortString);
        int indexOf = hostPortString.indexOf(58);
        int lastIndexOf = hostPortString.lastIndexOf(93);
        Preconditions.checkArgument(indexOf > -1 && lastIndexOf > indexOf, "Invalid bracketed host/port: %s", hostPortString);
        String substring = hostPortString.substring(1, lastIndexOf);
        int i = lastIndexOf + 1;
        if (i == hostPortString.length()) {
            return new String[]{substring, ""};
        }
        Preconditions.checkArgument(hostPortString.charAt(i) == ':', "Only a colon may follow a close bracket: %s", hostPortString);
        int i2 = lastIndexOf + 2;
        for (int i3 = i2; i3 < hostPortString.length(); i3++) {
            Preconditions.checkArgument(Character.isDigit(hostPortString.charAt(i3)), "Port must be numeric: %s", hostPortString);
        }
        return new String[]{substring, hostPortString.substring(i2)};
    }

    public HostAndPort withDefaultPort(int defaultPort) {
        Preconditions.checkArgument(isValidPort(defaultPort));
        return hasPort() ? this : new HostAndPort(this.host, defaultPort, this.hasBracketlessColons);
    }

    public HostAndPort requireBracketsForIPv6() {
        Preconditions.checkArgument(!this.hasBracketlessColons, "Possible bracketless IPv6 literal: %s", this.host);
        return this;
    }

    public boolean equals(@CheckForNull Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostAndPort)) {
            return false;
        }
        HostAndPort hostAndPort = (HostAndPort) other;
        return Objects.equal(this.host, hostAndPort.host) && this.port == hostAndPort.port;
    }

    public int hashCode() {
        return Objects.hashCode(this.host, Integer.valueOf(this.port));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.host.length() + 8);
        if (this.host.indexOf(58) >= 0) {
            sb.append('[');
            sb.append(this.host);
            sb.append(']');
        } else {
            sb.append(this.host);
        }
        if (hasPort()) {
            sb.append(':');
            sb.append(this.port);
        }
        return sb.toString();
    }
}

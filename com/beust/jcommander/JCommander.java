package com.beust.jcommander;

import androidx.webkit.Profile;
import com.beust.jcommander.FuzzyMap;
import com.beust.jcommander.converters.IParameterSplitter;
import com.beust.jcommander.converters.NoConverter;
import com.beust.jcommander.converters.StringConverter;
import com.beust.jcommander.internal.Console;
import com.beust.jcommander.internal.DefaultConsole;
import com.beust.jcommander.internal.DefaultConverterFactory;
import com.beust.jcommander.internal.JDK6Console;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import com.beust.jcommander.internal.Nullable;
import com.facebook.internal.ServerProtocol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public class JCommander {
    private static LinkedList<IStringConverterFactory> CONVERTER_FACTORIES = null;
    public static final String DEBUG_PROPERTY = "jcommander.debug";
    private static Console m_console;
    private java.util.ResourceBundle m_bundle;
    private IDefaultProvider m_defaultProvider;
    private Map<FuzzyMap.IKey, ParameterDescription> m_descriptions;
    private boolean m_helpWasSpecified;
    private Parameter m_mainParameterAnnotation;
    private ParameterDescription m_mainParameterDescription;
    private Object m_mainParameterObject;
    private String m_parsedAlias;
    private String m_parsedCommand;
    private ProgramName m_programName;
    private List<Object> m_objects = Lists.newArrayList();
    private boolean m_firstTimeMainParameter = true;
    private Parameterized m_mainParameter = null;
    private Map<Parameterized, ParameterDescription> m_requiredFields = Maps.newHashMap();
    private Map<Parameterized, ParameterDescription> m_fields = Maps.newHashMap();
    private Map<ProgramName, JCommander> m_commands = Maps.newLinkedHashMap();
    private Map<FuzzyMap.IKey, ProgramName> aliasMap = Maps.newLinkedHashMap();
    private Comparator<? super ParameterDescription> m_parameterDescriptionComparator = new Comparator<ParameterDescription>() { // from class: com.beust.jcommander.JCommander.1
        @Override // java.util.Comparator
        public int compare(ParameterDescription parameterDescription, ParameterDescription parameterDescription2) {
            return parameterDescription.getLongestName().compareTo(parameterDescription2.getLongestName());
        }
    };
    private int m_columnSize = 79;
    private List<String> m_unknownArgs = Lists.newArrayList();
    private boolean m_acceptUnknownOptions = false;
    private boolean m_allowParameterOverwriting = false;
    private final IVariableArity DEFAULT_VARIABLE_ARITY = new DefaultVariableArity();
    private int m_verbose = 0;
    private boolean m_caseSensitiveOptions = true;
    private boolean m_allowAbbreviatedOptions = false;

    private static String pluralize(int i, String str, String str2) {
        return i == 1 ? str : str2;
    }

    static {
        LinkedList<IStringConverterFactory> newLinkedList = Lists.newLinkedList();
        CONVERTER_FACTORIES = newLinkedList;
        newLinkedList.addFirst(new DefaultConverterFactory());
    }

    public JCommander() {
    }

    public JCommander(Object obj) {
        addObject(obj);
        createDescriptions();
    }

    public JCommander(Object obj, @Nullable java.util.ResourceBundle resourceBundle) {
        addObject(obj);
        setDescriptionsBundle(resourceBundle);
    }

    public JCommander(Object obj, java.util.ResourceBundle resourceBundle, String... strArr) {
        addObject(obj);
        setDescriptionsBundle(resourceBundle);
        parse(strArr);
    }

    public JCommander(Object obj, String... strArr) {
        addObject(obj);
        parse(strArr);
    }

    public static Console getConsole() {
        if (m_console == null) {
            try {
                m_console = new JDK6Console(System.class.getDeclaredMethod("console", new Class[0]).invoke(null, new Object[0]));
            } catch (Throwable unused) {
                m_console = new DefaultConsole();
            }
        }
        return m_console;
    }

    public final void addObject(Object obj) {
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                this.m_objects.add(it.next());
            }
            return;
        }
        if (obj.getClass().isArray()) {
            for (Object obj2 : (Object[]) obj) {
                this.m_objects.add(obj2);
            }
            return;
        }
        this.m_objects.add(obj);
    }

    public final void setDescriptionsBundle(java.util.ResourceBundle resourceBundle) {
        this.m_bundle = resourceBundle;
    }

    public void parse(String... strArr) {
        parse(true, strArr);
    }

    public void parseWithoutValidation(String... strArr) {
        parse(false, strArr);
    }

    private void parse(boolean z, String... strArr) {
        StringBuilder sb = new StringBuilder("Parsing \"");
        StringBuilder join = join(strArr);
        join.append("\"\n  with:");
        join.append((CharSequence) join(this.m_objects.toArray()));
        sb.append((CharSequence) join);
        p(sb.toString());
        if (this.m_descriptions == null) {
            createDescriptions();
        }
        initializeDefaultValues();
        parseValues(expandArgs(strArr), z);
        if (z) {
            validateOptions();
        }
    }

    private StringBuilder join(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(objArr[i]);
        }
        return sb;
    }

    private void initializeDefaultValues() {
        if (this.m_defaultProvider != null) {
            Iterator<ParameterDescription> it = this.m_descriptions.values().iterator();
            while (it.hasNext()) {
                initializeDefaultValue(it.next());
            }
            Iterator<Map.Entry<ProgramName, JCommander>> it2 = this.m_commands.entrySet().iterator();
            while (it2.hasNext()) {
                it2.next().getValue().initializeDefaultValues();
            }
        }
    }

    private void validateOptions() {
        if (this.m_helpWasSpecified) {
            return;
        }
        if (!this.m_requiredFields.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator<ParameterDescription> it = this.m_requiredFields.values().iterator();
            while (it.hasNext()) {
                sb.append(it.next().getNames());
                sb.append(" ");
            }
            throw new ParameterException("The following " + pluralize(this.m_requiredFields.size(), "option is required: ", "options are required: ") + ((Object) sb));
        }
        ParameterDescription parameterDescription = this.m_mainParameterDescription;
        if (parameterDescription == null || !parameterDescription.getParameter().required() || this.m_mainParameterDescription.isAssigned()) {
            return;
        }
        throw new ParameterException("Main parameters are required (\"" + this.m_mainParameterDescription.getDescription() + "\")");
    }

    private String[] expandArgs(String[] strArr) {
        List newArrayList = Lists.newArrayList();
        for (String str : strArr) {
            if (str.startsWith("@")) {
                newArrayList.addAll(readFile(str.substring(1)));
            } else {
                newArrayList.addAll(expandDynamicArg(str));
            }
        }
        List newArrayList2 = Lists.newArrayList();
        for (int i = 0; i < newArrayList.size(); i++) {
            String str2 = (String) newArrayList.get(i);
            String[] strArr2 = (String[]) newArrayList.toArray(new String[0]);
            if (isOption(strArr2, str2)) {
                String separatorFor = getSeparatorFor(strArr2, str2);
                if (!" ".equals(separatorFor)) {
                    String[] split = str2.split("[" + separatorFor + "]", 2);
                    int length = split.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        newArrayList2.add(split[i2]);
                    }
                } else {
                    newArrayList2.add(str2);
                }
            } else {
                newArrayList2.add(str2);
            }
        }
        return (String[]) newArrayList2.toArray(new String[newArrayList2.size()]);
    }

    private List<String> expandDynamicArg(String str) {
        for (ParameterDescription parameterDescription : this.m_descriptions.values()) {
            if (parameterDescription.isDynamicParameter()) {
                for (String str2 : parameterDescription.getParameter().names()) {
                    if (str.startsWith(str2) && !str.equals(str2)) {
                        return Arrays.asList(str2, str.substring(str2.length()));
                    }
                }
            }
        }
        return Arrays.asList(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOption(String[] strArr, String str) {
        return str.length() > 0 && getOptionPrefixes(strArr, str).indexOf(str.charAt(0)) >= 0;
    }

    private ParameterDescription getPrefixDescriptionFor(String str) {
        for (Map.Entry<FuzzyMap.IKey, ParameterDescription> entry : this.m_descriptions.entrySet()) {
            if (str.startsWith(entry.getKey().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private ParameterDescription getDescriptionFor(String[] strArr, String str) {
        ParameterDescription prefixDescriptionFor = getPrefixDescriptionFor(str);
        if (prefixDescriptionFor != null) {
            return prefixDescriptionFor;
        }
        for (String str2 : strArr) {
            ParameterDescription prefixDescriptionFor2 = getPrefixDescriptionFor(str);
            if (prefixDescriptionFor2 != null) {
                prefixDescriptionFor = prefixDescriptionFor2;
            }
            if (str2.equals(str)) {
                return prefixDescriptionFor;
            }
        }
        throw new ParameterException("Unknown parameter: " + str);
    }

    private String getSeparatorFor(String[] strArr, String str) {
        Parameters parameters;
        ParameterDescription descriptionFor = getDescriptionFor(strArr, str);
        return (descriptionFor == null || (parameters = (Parameters) descriptionFor.getObject().getClass().getAnnotation(Parameters.class)) == null) ? " " : parameters.separators();
    }

    private String getOptionPrefixes(String[] strArr, String str) {
        Parameters parameters;
        ParameterDescription descriptionFor = getDescriptionFor(strArr, str);
        if (descriptionFor != null && (parameters = (Parameters) descriptionFor.getObject().getClass().getAnnotation(Parameters.class)) != null) {
            return parameters.optionPrefixes();
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = this.m_objects.iterator();
        while (it.hasNext()) {
            Parameters parameters2 = (Parameters) it.next().getClass().getAnnotation(Parameters.class);
            if (parameters2 != null && !Parameters.DEFAULT_OPTION_PREFIXES.equals(parameters2.optionPrefixes())) {
                sb.append(parameters2.optionPrefixes());
            }
        }
        return !Strings.isStringEmpty(sb.toString()) ? sb.toString() : Parameters.DEFAULT_OPTION_PREFIXES;
    }

    private static List<String> readFile(String str) {
        List<String> newArrayList = Lists.newArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (readLine.length() > 0 && !readLine.trim().startsWith("#")) {
                        newArrayList.add(readLine);
                    }
                } else {
                    bufferedReader.close();
                    return newArrayList;
                }
            }
        } catch (IOException e) {
            throw new ParameterException("Could not read file " + str + ": " + e);
        }
    }

    private static String trim(String str) {
        String trim = str.trim();
        return (trim.startsWith("\"") && trim.endsWith("\"") && trim.length() > 1) ? trim.substring(1, trim.length() - 1) : trim;
    }

    private void createDescriptions() {
        this.m_descriptions = Maps.newHashMap();
        Iterator<Object> it = this.m_objects.iterator();
        while (it.hasNext()) {
            addDescription(it.next());
        }
    }

    private void addDescription(Object obj) {
        Object obj2 = obj;
        obj.getClass();
        Iterator<Parameterized> it = Parameterized.parseArg(obj).iterator();
        while (it.hasNext()) {
            Parameterized next = it.next();
            WrappedParameter wrappedParameter = next.getWrappedParameter();
            int i = 0;
            if (wrappedParameter != null && wrappedParameter.getParameter() != null) {
                Parameter parameter = wrappedParameter.getParameter();
                if (parameter.names().length == 0) {
                    p("Found main parameter:" + next);
                    if (this.m_mainParameter != null) {
                        throw new ParameterException("Only one @Parameter with no names attribute is allowed, found:" + this.m_mainParameter + " and " + next);
                    }
                    this.m_mainParameter = next;
                    this.m_mainParameterObject = obj2;
                    this.m_mainParameterAnnotation = parameter;
                    this.m_mainParameterDescription = new ParameterDescription(obj, parameter, next, this.m_bundle, this);
                } else {
                    ParameterDescription parameterDescription = new ParameterDescription(obj, parameter, next, this.m_bundle, this);
                    String[] names = parameter.names();
                    int length = names.length;
                    while (i < length) {
                        String str = names[i];
                        if (this.m_descriptions.containsKey(new StringKey(str))) {
                            throw new ParameterException("Found the option " + str + " multiple times");
                        }
                        p("Adding description for " + str);
                        this.m_fields.put(next, parameterDescription);
                        this.m_descriptions.put(new StringKey(str), parameterDescription);
                        if (parameter.required()) {
                            this.m_requiredFields.put(next, parameterDescription);
                        }
                        i++;
                    }
                }
            } else if (next.getDelegateAnnotation() != null) {
                Object obj3 = next.get(obj2);
                if (obj3 == null) {
                    throw new ParameterException("Delegate field '" + next.getName() + "' cannot be null.");
                }
                addDescription(obj3);
            } else if (wrappedParameter != null && wrappedParameter.getDynamicParameter() != null) {
                DynamicParameter dynamicParameter = wrappedParameter.getDynamicParameter();
                String[] names2 = dynamicParameter.names();
                int length2 = names2.length;
                while (i < length2) {
                    String str2 = names2[i];
                    if (this.m_descriptions.containsKey(str2)) {
                        throw new ParameterException("Found the option " + str2 + " multiple times");
                    }
                    p("Adding description for " + str2);
                    Iterator<Parameterized> it2 = it;
                    int i2 = length2;
                    ParameterDescription parameterDescription2 = new ParameterDescription(obj, dynamicParameter, next, this.m_bundle, this);
                    this.m_fields.put(next, parameterDescription2);
                    this.m_descriptions.put(new StringKey(str2), parameterDescription2);
                    if (dynamicParameter.required()) {
                        this.m_requiredFields.put(next, parameterDescription2);
                    }
                    i++;
                    length2 = i2;
                    it = it2;
                }
            }
            obj2 = obj;
            it = it;
        }
    }

    private void initializeDefaultValue(ParameterDescription parameterDescription) {
        for (String str : parameterDescription.getParameter().names()) {
            String defaultValueFor = this.m_defaultProvider.getDefaultValueFor(str);
            if (defaultValueFor != null) {
                p("Initializing " + str + " with default value:" + defaultValueFor);
                parameterDescription.addValue(defaultValueFor, true);
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0185, code lost:
    
        r12 = r11.m_descriptions.values().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0193, code lost:
    
        if (r12.hasNext() == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0195, code lost:
    
        r13 = r12.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x019f, code lost:
    
        if (r13.isAssigned() == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01a1, code lost:
    
        r11.m_fields.get(r13.getParameterized()).setAssigned(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01b1, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseValues(String[] strArr, boolean z) {
        Object obj;
        int i;
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            int i3 = 1;
            if (i2 >= strArr.length || z2) {
                break;
            }
            String str = strArr[i2];
            String trim = trim(str);
            strArr[i2] = trim;
            p("Parsing arg: " + trim);
            JCommander findCommandByAlias = findCommandByAlias(str);
            if (!z3 && !"--".equals(trim) && isOption(strArr, trim) && findCommandByAlias == null) {
                ParameterDescription findParameterDescription = findParameterDescription(trim);
                if (findParameterDescription != null) {
                    if (findParameterDescription.getParameter().password()) {
                        findParameterDescription.addValue(new String(readPassword(findParameterDescription.getDescription(), findParameterDescription.getParameter().echoInput())));
                        this.m_requiredFields.remove(findParameterDescription.getParameterized());
                    } else {
                        if (findParameterDescription.getParameter().variableArity()) {
                            i = processVariableArity(strArr, i2, findParameterDescription);
                        } else {
                            Class<?> type = findParameterDescription.getParameterized().getType();
                            if ((type == Boolean.TYPE || type == Boolean.class) && findParameterDescription.getParameter().arity() == -1) {
                                findParameterDescription.addValue(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                this.m_requiredFields.remove(findParameterDescription.getParameterized());
                                i = 1;
                            } else {
                                i = processFixedArity(strArr, i2, findParameterDescription, type);
                            }
                            if (findParameterDescription.isHelp()) {
                                this.m_helpWasSpecified = true;
                            }
                        }
                        i3 = i;
                    }
                } else if (this.m_acceptUnknownOptions) {
                    this.m_unknownArgs.add(str);
                    i2++;
                    while (i2 < strArr.length && !isOption(strArr, strArr[i2])) {
                        this.m_unknownArgs.add(strArr[i2]);
                        i2++;
                    }
                    i3 = 0;
                } else {
                    throw new ParameterException("Unknown option: " + str);
                }
            } else if (Strings.isStringEmpty(str)) {
                continue;
            } else {
                if ("--".equals(str)) {
                    i2++;
                    trim = trim(strArr[i2]);
                    z3 = true;
                }
                if (this.m_commands.isEmpty()) {
                    List<?> mainParameter = getMainParameter(str);
                    if (this.m_mainParameter.getGenericType() instanceof ParameterizedType) {
                        Type type2 = ((ParameterizedType) this.m_mainParameter.getGenericType()).getActualTypeArguments()[0];
                        if (type2 instanceof Class) {
                            obj = convertValue(this.m_mainParameter, (Class) type2, trim);
                            ParameterDescription.validateParameter(this.m_mainParameterDescription, this.m_mainParameterAnnotation.validateWith(), Profile.DEFAULT_PROFILE_NAME, trim);
                            this.m_mainParameterDescription.setAssigned(true);
                            mainParameter.add(obj);
                        }
                    }
                    obj = trim;
                    ParameterDescription.validateParameter(this.m_mainParameterDescription, this.m_mainParameterAnnotation.validateWith(), Profile.DEFAULT_PROFILE_NAME, trim);
                    this.m_mainParameterDescription.setAssigned(true);
                    mainParameter.add(obj);
                } else {
                    if (findCommandByAlias == null && z) {
                        throw new MissingCommandException("Expected a command, got " + str);
                    }
                    if (findCommandByAlias != null) {
                        this.m_parsedCommand = findCommandByAlias.m_programName.m_name;
                        this.m_parsedAlias = str;
                        findCommandByAlias.parse(subArray(strArr, i2 + 1));
                        z2 = true;
                    }
                }
            }
            i2 += i3;
        }
    }

    /* loaded from: classes2.dex */
    private class DefaultVariableArity implements IVariableArity {
        private DefaultVariableArity() {
        }

        @Override // com.beust.jcommander.IVariableArity
        public int processVariableArity(String str, String[] strArr) {
            int i = 0;
            while (i < strArr.length && !JCommander.this.isOption(strArr, strArr[i])) {
                i++;
            }
            return i;
        }
    }

    private int processVariableArity(String[] strArr, int i, ParameterDescription parameterDescription) {
        IVariableArity iVariableArity;
        Object object = parameterDescription.getObject();
        if (!(object instanceof IVariableArity)) {
            iVariableArity = this.DEFAULT_VARIABLE_ARITY;
        } else {
            iVariableArity = (IVariableArity) object;
        }
        List newArrayList = Lists.newArrayList();
        for (int i2 = i + 1; i2 < strArr.length; i2++) {
            newArrayList.add(strArr[i2]);
        }
        return processFixedArity(strArr, i, parameterDescription, List.class, iVariableArity.processVariableArity(parameterDescription.getParameter().names()[0], (String[]) newArrayList.toArray(new String[0])));
    }

    private int processFixedArity(String[] strArr, int i, ParameterDescription parameterDescription, Class<?> cls) {
        int arity = parameterDescription.getParameter().arity();
        if (arity == -1) {
            arity = 1;
        }
        return processFixedArity(strArr, i, parameterDescription, cls, arity);
    }

    private int processFixedArity(String[] strArr, int i, ParameterDescription parameterDescription, Class<?> cls, int i2) {
        String str = strArr[i];
        if (i2 == 0 && (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls))) {
            parameterDescription.addValue(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            this.m_requiredFields.remove(parameterDescription.getParameterized());
        } else if (i < strArr.length - 1) {
            boolean equals = "--".equals(strArr[i + 1]);
            if (i + i2 >= strArr.length) {
                throw new ParameterException("Expected " + i2 + " values after " + str);
            }
            for (int i3 = 1; i3 <= i2; i3++) {
                parameterDescription.addValue(trim(strArr[i + i3 + (equals ? 1 : 0)]));
                this.m_requiredFields.remove(parameterDescription.getParameterized());
            }
        } else {
            throw new ParameterException("Expected a value after parameter " + str);
        }
        return i2 + 1;
    }

    private char[] readPassword(String str, boolean z) {
        getConsole().print(str + ": ");
        return getConsole().readPassword(z);
    }

    private String[] subArray(String[] strArr, int i) {
        int length = strArr.length - i;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, i, strArr2, 0, length);
        return strArr2;
    }

    private List<?> getMainParameter(String str) {
        Parameterized parameterized = this.m_mainParameter;
        if (parameterized == null) {
            throw new ParameterException("Was passed main parameter '" + str + "' but no main parameter was defined");
        }
        List<?> list = (List) parameterized.get(this.m_mainParameterObject);
        if (list == null) {
            list = Lists.newArrayList();
            if (!List.class.isAssignableFrom(this.m_mainParameter.getType())) {
                throw new ParameterException("Main parameter field " + this.m_mainParameter + " needs to be of type List, not " + this.m_mainParameter.getType());
            }
            this.m_mainParameter.set(this.m_mainParameterObject, list);
        }
        if (this.m_firstTimeMainParameter) {
            list.clear();
            this.m_firstTimeMainParameter = false;
        }
        return list;
    }

    public String getMainParameterDescription() {
        if (this.m_descriptions == null) {
            createDescriptions();
        }
        Parameter parameter = this.m_mainParameterAnnotation;
        if (parameter != null) {
            return parameter.description();
        }
        return null;
    }

    public void setProgramName(String str) {
        setProgramName(str, new String[0]);
    }

    public void setProgramName(String str, String... strArr) {
        this.m_programName = new ProgramName(str, Arrays.asList(strArr));
    }

    public void usage(String str) {
        StringBuilder sb = new StringBuilder();
        usage(str, sb);
        getConsole().println(sb.toString());
    }

    public void usage(String str, StringBuilder sb) {
        usage(str, sb, "");
    }

    public void usage(String str, StringBuilder sb, String str2) {
        String commandDescription = getCommandDescription(str);
        JCommander findCommandByAlias = findCommandByAlias(str);
        if (commandDescription != null) {
            sb.append(str2);
            sb.append(commandDescription);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        findCommandByAlias.usage(sb, str2);
    }

    public String getCommandDescription(String str) {
        java.util.ResourceBundle resourceBundle;
        JCommander findCommandByAlias = findCommandByAlias(str);
        if (findCommandByAlias == null) {
            throw new ParameterException("Asking description for unknown command: " + str);
        }
        Parameters parameters = (Parameters) findCommandByAlias.getObjects().get(0).getClass().getAnnotation(Parameters.class);
        if (parameters == null) {
            return null;
        }
        String commandDescription = parameters.commandDescription();
        String resourceBundle2 = parameters.resourceBundle();
        if (!"".equals(resourceBundle2)) {
            resourceBundle = java.util.ResourceBundle.getBundle(resourceBundle2, Locale.getDefault());
        } else {
            resourceBundle = this.m_bundle;
        }
        return resourceBundle != null ? getI18nString(resourceBundle, parameters.commandDescriptionKey(), parameters.commandDescription()) : commandDescription;
    }

    private String getI18nString(java.util.ResourceBundle resourceBundle, String str, String str2) {
        String string = resourceBundle != null ? resourceBundle.getString(str) : null;
        return string != null ? string : str2;
    }

    public void usage() {
        StringBuilder sb = new StringBuilder();
        usage(sb);
        getConsole().println(sb.toString());
    }

    public void usage(StringBuilder sb) {
        usage(sb, "");
    }

    public void usage(StringBuilder sb, String str) {
        if (this.m_descriptions == null) {
            createDescriptions();
        }
        boolean z = !this.m_commands.isEmpty();
        ProgramName programName = this.m_programName;
        String displayName = programName != null ? programName.getDisplayName() : "<main class>";
        sb.append(str);
        sb.append("Usage: " + displayName + " [options]");
        if (z) {
            sb.append(str);
            sb.append(" [command] [command options]");
        }
        if (this.m_mainParameterDescription != null) {
            sb.append(" " + this.m_mainParameterDescription.getDescription());
        }
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        List<ParameterDescription> newArrayList = Lists.newArrayList();
        int i = 0;
        for (ParameterDescription parameterDescription : this.m_fields.values()) {
            if (!parameterDescription.getParameter().hidden()) {
                newArrayList.add(parameterDescription);
                int length = parameterDescription.getNames().length() + 2;
                if (length > i) {
                    i = length;
                }
            }
        }
        Collections.sort(newArrayList, getParameterDescriptionComparator());
        if (newArrayList.size() > 0) {
            sb.append(str);
            sb.append("  Options:\n");
        }
        for (ParameterDescription parameterDescription2 : newArrayList) {
            WrappedParameter parameter = parameterDescription2.getParameter();
            sb.append(str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  ");
            sb2.append(parameter.required() ? "* " : "  ");
            sb2.append(parameterDescription2.getNames());
            sb2.append(IOUtils.LINE_SEPARATOR_UNIX);
            sb2.append(str);
            sb2.append(s(6));
            sb.append(sb2.toString());
            int length2 = str.length() + 6;
            wrapDescription(sb, length2, parameterDescription2.getDescription());
            Object obj = parameterDescription2.getDefault();
            if (parameterDescription2.isDynamicParameter()) {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX + s(length2 + 1));
                sb.append("Syntax: " + parameter.names()[0] + "key" + parameter.getAssignment() + "value");
            }
            if (obj != null) {
                String obj2 = Strings.isStringEmpty(obj.toString()) ? "<empty string>" : obj.toString();
                sb.append(IOUtils.LINE_SEPARATOR_UNIX + s(length2 + 1));
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Default: ");
                if (parameter.password()) {
                    obj2 = "********";
                }
                sb3.append(obj2);
                sb.append(sb3.toString());
            }
            Class<?> type = parameterDescription2.getParameterized().getType();
            if (type.isEnum()) {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX + s(length2 + 1));
                sb.append("Possible Values: " + EnumSet.allOf(type));
            }
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        if (z) {
            sb.append("  Commands:\n");
            for (Map.Entry<ProgramName, JCommander> entry : this.m_commands.entrySet()) {
                if (!((Parameters) entry.getValue().getObjects().get(0).getClass().getAnnotation(Parameters.class)).hidden()) {
                    ProgramName key = entry.getKey();
                    String displayName2 = key.getDisplayName();
                    sb.append(str);
                    sb.append("    " + displayName2);
                    usage(key.getName(), sb, "      ");
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
        }
    }

    private Comparator<? super ParameterDescription> getParameterDescriptionComparator() {
        return this.m_parameterDescriptionComparator;
    }

    public void setParameterDescriptionComparator(Comparator<? super ParameterDescription> comparator) {
        this.m_parameterDescriptionComparator = comparator;
    }

    public void setColumnSize(int i) {
        this.m_columnSize = i;
    }

    public int getColumnSize() {
        return this.m_columnSize;
    }

    private void wrapDescription(StringBuilder sb, int i, String str) {
        int columnSize = getColumnSize();
        int i2 = i;
        for (String str2 : str.split(" ")) {
            if (str2.length() > columnSize || str2.length() + i2 <= columnSize) {
                sb.append(" ");
                sb.append(str2);
                i2 += str2.length() + 1;
            } else {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append(s(i + 1));
                sb.append(str2);
                i2 = i;
            }
        }
    }

    public List<ParameterDescription> getParameters() {
        return new ArrayList(this.m_fields.values());
    }

    public ParameterDescription getMainParameter() {
        return this.m_mainParameterDescription;
    }

    private void p(String str) {
        if (this.m_verbose > 0 || System.getProperty(DEBUG_PROPERTY) != null) {
            getConsole().println("[JCommander] " + str);
        }
    }

    public void setDefaultProvider(IDefaultProvider iDefaultProvider) {
        this.m_defaultProvider = iDefaultProvider;
        Iterator<Map.Entry<ProgramName, JCommander>> it = this.m_commands.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setDefaultProvider(iDefaultProvider);
        }
    }

    public void addConverterFactory(IStringConverterFactory iStringConverterFactory) {
        CONVERTER_FACTORIES.addFirst(iStringConverterFactory);
    }

    public <T> Class<? extends IStringConverter<T>> findConverter(Class<T> cls) {
        Iterator<IStringConverterFactory> it = CONVERTER_FACTORIES.iterator();
        while (it.hasNext()) {
            Class<? extends IStringConverter<T>> converter = it.next().getConverter(cls);
            if (converter != null) {
                return converter;
            }
        }
        return null;
    }

    public Object convertValue(ParameterDescription parameterDescription, String str) {
        return convertValue(parameterDescription.getParameterized(), parameterDescription.getParameterized().getType(), str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0049, code lost:
    
        if (java.lang.Enum.class.isAssignableFrom(r3) != false) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object convertValue(Parameterized parameterized, Class cls, String str) {
        Parameter parameter = parameterized.getParameter();
        if (parameter == null) {
            return str;
        }
        Class converter = parameter.converter();
        boolean z = parameter.listConverter() != NoConverter.class;
        if (converter == null || converter == NoConverter.class) {
            converter = cls.isEnum() ? cls : findConverter(cls);
        }
        if (converter == null) {
            Type findFieldGenericType = parameterized.findFieldGenericType();
            Class findConverter = findFieldGenericType != null ? findConverter((Class) findFieldGenericType) : StringConverter.class;
            if (findConverter == null) {
                converter = (Class) findFieldGenericType;
            }
            converter = findConverter;
        }
        try {
            String[] names = parameter.names();
            String str2 = names.length > 0 ? names[0] : "[Main class]";
            if (converter != null && converter.isEnum()) {
                try {
                    try {
                        return Enum.valueOf(converter, str);
                    } catch (IllegalArgumentException unused) {
                        throw new ParameterException("Invalid value for " + str2 + " parameter. Allowed values:" + EnumSet.allOf(converter));
                    }
                } catch (IllegalArgumentException unused2) {
                    return Enum.valueOf(converter, str.toUpperCase());
                } catch (Exception unused3) {
                    throw new ParameterException("Invalid value for " + str2 + " parameter. Allowed values:" + EnumSet.allOf(converter));
                }
            }
            IStringConverter<?> instantiateConverter = instantiateConverter(str2, converter);
            if (!cls.isAssignableFrom(List.class) || !(parameterized.getGenericType() instanceof ParameterizedType)) {
                return instantiateConverter.convert(str);
            }
            if (z) {
                return instantiateConverter(str2, parameter.listConverter()).convert(str);
            }
            return convertToList(str, instantiateConverter, parameter.splitter());
        } catch (IllegalAccessException e) {
            throw new ParameterException(e);
        } catch (InstantiationException e2) {
            throw new ParameterException(e2);
        } catch (InvocationTargetException e3) {
            throw new ParameterException(e3);
        }
    }

    private Object convertToList(String str, IStringConverter<?> iStringConverter, Class<? extends IParameterSplitter> cls) throws InstantiationException, IllegalAccessException {
        IParameterSplitter newInstance = cls.newInstance();
        List newArrayList = Lists.newArrayList();
        Iterator<String> it = newInstance.split(str).iterator();
        while (it.hasNext()) {
            newArrayList.add(iStringConverter.convert(it.next()));
        }
        return newArrayList;
    }

    private IStringConverter<?> instantiateConverter(String str, Class<? extends IStringConverter<?>> cls) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Object newInstance;
        Constructor<?> constructor = null;
        Constructor<?> constructor2 = null;
        for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(String.class)) {
                constructor = constructor3;
            } else if (parameterTypes.length == 0) {
                constructor2 = constructor3;
            }
        }
        if (constructor != null) {
            newInstance = constructor.newInstance(str);
        } else {
            if (constructor2 == null) {
                return null;
            }
            newInstance = constructor2.newInstance(new Object[0]);
        }
        return (IStringConverter) newInstance;
    }

    public void addCommand(String str, Object obj) {
        addCommand(str, obj, new String[0]);
    }

    public void addCommand(Object obj) {
        Parameters parameters = (Parameters) obj.getClass().getAnnotation(Parameters.class);
        if (parameters != null && parameters.commandNames().length > 0) {
            for (String str : parameters.commandNames()) {
                addCommand(str, obj);
            }
            return;
        }
        throw new ParameterException("Trying to add command " + obj.getClass().getName() + " without specifying its names in @Parameters");
    }

    public void addCommand(String str, Object obj, String... strArr) {
        JCommander jCommander = new JCommander(obj);
        jCommander.setProgramName(str, strArr);
        jCommander.setDefaultProvider(this.m_defaultProvider);
        jCommander.setAcceptUnknownOptions(this.m_acceptUnknownOptions);
        ProgramName programName = jCommander.m_programName;
        this.m_commands.put(programName, jCommander);
        this.aliasMap.put(new StringKey(str), programName);
        for (String str2 : strArr) {
            StringKey stringKey = new StringKey(str2);
            if (!stringKey.equals(str)) {
                ProgramName programName2 = this.aliasMap.get(stringKey);
                if (programName2 != null && !programName2.equals(programName)) {
                    throw new ParameterException("Cannot set alias " + stringKey + " for " + str + " command because it has already been defined for " + programName2.m_name + " command");
                }
                this.aliasMap.put(stringKey, programName);
            }
        }
    }

    public Map<String, JCommander> getCommands() {
        Map<String, JCommander> newLinkedHashMap = Maps.newLinkedHashMap();
        for (Map.Entry<ProgramName, JCommander> entry : this.m_commands.entrySet()) {
            newLinkedHashMap.put(entry.getKey().m_name, entry.getValue());
        }
        return newLinkedHashMap;
    }

    public String getParsedCommand() {
        return this.m_parsedCommand;
    }

    public String getParsedAlias() {
        return this.m_parsedAlias;
    }

    private String s(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public List<Object> getObjects() {
        return this.m_objects;
    }

    private ParameterDescription findParameterDescription(String str) {
        return (ParameterDescription) FuzzyMap.findInMap(this.m_descriptions, new StringKey(str), this.m_caseSensitiveOptions, this.m_allowAbbreviatedOptions);
    }

    private JCommander findCommand(ProgramName programName) {
        return (JCommander) FuzzyMap.findInMap(this.m_commands, programName, this.m_caseSensitiveOptions, this.m_allowAbbreviatedOptions);
    }

    private ProgramName findProgramName(String str) {
        return (ProgramName) FuzzyMap.findInMap(this.aliasMap, new StringKey(str), this.m_caseSensitiveOptions, this.m_allowAbbreviatedOptions);
    }

    private JCommander findCommandByAlias(String str) {
        ProgramName findProgramName = findProgramName(str);
        if (findProgramName == null) {
            return null;
        }
        JCommander findCommand = findCommand(findProgramName);
        if (findCommand != null) {
            return findCommand;
        }
        throw new IllegalStateException("There appears to be inconsistency in the internal command database.  This is likely a bug. Please report.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ProgramName implements FuzzyMap.IKey {
        private final List<String> m_aliases;
        private final String m_name;

        ProgramName(String str, List<String> list) {
            this.m_name = str;
            this.m_aliases = list;
        }

        @Override // com.beust.jcommander.FuzzyMap.IKey
        public String getName() {
            return this.m_name;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getDisplayName() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.m_name);
            if (!this.m_aliases.isEmpty()) {
                sb.append("(");
                Iterator<String> it = this.m_aliases.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    if (it.hasNext()) {
                        sb.append(",");
                    }
                }
                sb.append(")");
            }
            return sb.toString();
        }

        public int hashCode() {
            String str = this.m_name;
            return 31 + (str == null ? 0 : str.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ProgramName programName = (ProgramName) obj;
            String str = this.m_name;
            if (str == null) {
                if (programName.m_name != null) {
                    return false;
                }
            } else if (!str.equals(programName.m_name)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return getDisplayName();
        }
    }

    public void setVerbose(int i) {
        this.m_verbose = i;
    }

    public void setCaseSensitiveOptions(boolean z) {
        this.m_caseSensitiveOptions = z;
    }

    public void setAllowAbbreviatedOptions(boolean z) {
        this.m_allowAbbreviatedOptions = z;
    }

    public void setAcceptUnknownOptions(boolean z) {
        this.m_acceptUnknownOptions = z;
    }

    public List<String> getUnknownOptions() {
        return this.m_unknownArgs;
    }

    public void setAllowParameterOverwriting(boolean z) {
        this.m_allowParameterOverwriting = z;
    }

    public boolean isParameterOverwritingAllowed() {
        return this.m_allowParameterOverwriting;
    }
}

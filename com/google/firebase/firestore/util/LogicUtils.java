package com.google.firebase.firestore.util;

import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.InFilter;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class LogicUtils {
    private static void assertFieldFilterOrCompositeFilter(Filter filter) {
        Assert.hardAssert((filter instanceof FieldFilter) || (filter instanceof CompositeFilter), "Only field filters and composite filters are accepted.", new Object[0]);
    }

    private static boolean isSingleFieldFilter(Filter filter) {
        return filter instanceof FieldFilter;
    }

    private static boolean isFlatConjunction(Filter filter) {
        return (filter instanceof CompositeFilter) && ((CompositeFilter) filter).isFlatConjunction();
    }

    private static boolean isDisjunctionOfFieldFiltersAndFlatConjunctions(Filter filter) {
        if (filter instanceof CompositeFilter) {
            CompositeFilter compositeFilter = (CompositeFilter) filter;
            if (compositeFilter.isDisjunction()) {
                for (Filter filter2 : compositeFilter.getFilters()) {
                    if (!isSingleFieldFilter(filter2) && !isFlatConjunction(filter2)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static boolean isDisjunctiveNormalForm(Filter filter) {
        return isSingleFieldFilter(filter) || isFlatConjunction(filter) || isDisjunctionOfFieldFiltersAndFlatConjunctions(filter);
    }

    protected static Filter applyAssociation(Filter filter) {
        assertFieldFilterOrCompositeFilter(filter);
        if (isSingleFieldFilter(filter)) {
            return filter;
        }
        CompositeFilter compositeFilter = (CompositeFilter) filter;
        List<Filter> filters = compositeFilter.getFilters();
        if (filters.size() == 1) {
            return applyAssociation(filters.get(0));
        }
        if (compositeFilter.isFlat()) {
            return compositeFilter;
        }
        ArrayList<Filter> arrayList = new ArrayList();
        Iterator<Filter> it = filters.iterator();
        while (it.hasNext()) {
            arrayList.add(applyAssociation(it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Filter filter2 : arrayList) {
            if (filter2 instanceof FieldFilter) {
                arrayList2.add(filter2);
            } else if (filter2 instanceof CompositeFilter) {
                CompositeFilter compositeFilter2 = (CompositeFilter) filter2;
                if (compositeFilter2.getOperator().equals(compositeFilter.getOperator())) {
                    arrayList2.addAll(compositeFilter2.getFilters());
                } else {
                    arrayList2.add(compositeFilter2);
                }
            }
        }
        if (arrayList2.size() == 1) {
            return (Filter) arrayList2.get(0);
        }
        return new CompositeFilter(arrayList2, compositeFilter.getOperator());
    }

    protected static Filter applyDistribution(Filter filter, Filter filter2) {
        Filter applyDistribution;
        assertFieldFilterOrCompositeFilter(filter);
        assertFieldFilterOrCompositeFilter(filter2);
        boolean z = filter instanceof FieldFilter;
        if (z && (filter2 instanceof FieldFilter)) {
            applyDistribution = applyDistribution((FieldFilter) filter, (FieldFilter) filter2);
        } else if (z && (filter2 instanceof CompositeFilter)) {
            applyDistribution = applyDistribution((FieldFilter) filter, (CompositeFilter) filter2);
        } else if ((filter instanceof CompositeFilter) && (filter2 instanceof FieldFilter)) {
            applyDistribution = applyDistribution((FieldFilter) filter2, (CompositeFilter) filter);
        } else {
            applyDistribution = applyDistribution((CompositeFilter) filter, (CompositeFilter) filter2);
        }
        return applyAssociation(applyDistribution);
    }

    private static Filter applyDistribution(FieldFilter fieldFilter, FieldFilter fieldFilter2) {
        return new CompositeFilter(Arrays.asList(fieldFilter, fieldFilter2), CompositeFilter.Operator.AND);
    }

    private static Filter applyDistribution(FieldFilter fieldFilter, CompositeFilter compositeFilter) {
        if (compositeFilter.isConjunction()) {
            return compositeFilter.withAddedFilters(Collections.singletonList(fieldFilter));
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Filter> it = compositeFilter.getFilters().iterator();
        while (it.hasNext()) {
            arrayList.add(applyDistribution(fieldFilter, it.next()));
        }
        return new CompositeFilter(arrayList, CompositeFilter.Operator.OR);
    }

    private static Filter applyDistribution(CompositeFilter compositeFilter, CompositeFilter compositeFilter2) {
        Assert.hardAssert((compositeFilter.getFilters().isEmpty() || compositeFilter2.getFilters().isEmpty()) ? false : true, "Found an empty composite filter", new Object[0]);
        if (compositeFilter.isConjunction() && compositeFilter2.isConjunction()) {
            return compositeFilter.withAddedFilters(compositeFilter2.getFilters());
        }
        CompositeFilter compositeFilter3 = compositeFilter.isDisjunction() ? compositeFilter : compositeFilter2;
        if (compositeFilter.isDisjunction()) {
            compositeFilter = compositeFilter2;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Filter> it = compositeFilter3.getFilters().iterator();
        while (it.hasNext()) {
            arrayList.add(applyDistribution(it.next(), compositeFilter));
        }
        return new CompositeFilter(arrayList, CompositeFilter.Operator.OR);
    }

    protected static Filter computeDistributedNormalForm(Filter filter) {
        assertFieldFilterOrCompositeFilter(filter);
        if (filter instanceof FieldFilter) {
            return filter;
        }
        CompositeFilter compositeFilter = (CompositeFilter) filter;
        if (compositeFilter.getFilters().size() == 1) {
            return computeDistributedNormalForm(filter.getFilters().get(0));
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Filter> it = compositeFilter.getFilters().iterator();
        while (it.hasNext()) {
            arrayList.add(computeDistributedNormalForm(it.next()));
        }
        Filter applyAssociation = applyAssociation(new CompositeFilter(arrayList, compositeFilter.getOperator()));
        if (isDisjunctiveNormalForm(applyAssociation)) {
            return applyAssociation;
        }
        Assert.hardAssert(applyAssociation instanceof CompositeFilter, "field filters are already in DNF form.", new Object[0]);
        CompositeFilter compositeFilter2 = (CompositeFilter) applyAssociation;
        Assert.hardAssert(compositeFilter2.isConjunction(), "Disjunction of filters all of which are already in DNF form is itself in DNF form.", new Object[0]);
        Assert.hardAssert(compositeFilter2.getFilters().size() > 1, "Single-filter composite filters are already in DNF form.", new Object[0]);
        Filter filter2 = compositeFilter2.getFilters().get(0);
        for (int i = 1; i < compositeFilter2.getFilters().size(); i++) {
            filter2 = applyDistribution(filter2, compositeFilter2.getFilters().get(i));
        }
        return filter2;
    }

    protected static Filter computeInExpansion(Filter filter) {
        assertFieldFilterOrCompositeFilter(filter);
        ArrayList arrayList = new ArrayList();
        if (filter instanceof FieldFilter) {
            if (!(filter instanceof InFilter)) {
                return filter;
            }
            InFilter inFilter = (InFilter) filter;
            Iterator<Value> it = inFilter.getValue().getArrayValue().getValuesList().iterator();
            while (it.hasNext()) {
                arrayList.add(FieldFilter.create(inFilter.getField(), FieldFilter.Operator.EQUAL, it.next()));
            }
            return new CompositeFilter(arrayList, CompositeFilter.Operator.OR);
        }
        CompositeFilter compositeFilter = (CompositeFilter) filter;
        Iterator<Filter> it2 = compositeFilter.getFilters().iterator();
        while (it2.hasNext()) {
            arrayList.add(computeInExpansion(it2.next()));
        }
        return new CompositeFilter(arrayList, compositeFilter.getOperator());
    }

    public static List<Filter> getDnfTerms(CompositeFilter compositeFilter) {
        if (compositeFilter.getFilters().isEmpty()) {
            return Collections.emptyList();
        }
        Filter computeDistributedNormalForm = computeDistributedNormalForm(computeInExpansion(compositeFilter));
        Assert.hardAssert(isDisjunctiveNormalForm(computeDistributedNormalForm), "computeDistributedNormalForm did not result in disjunctive normal form", new Object[0]);
        if (isSingleFieldFilter(computeDistributedNormalForm) || isFlatConjunction(computeDistributedNormalForm)) {
            return Collections.singletonList(computeDistributedNormalForm);
        }
        return computeDistributedNormalForm.getFilters();
    }
}

package org.azd.workitemtracking.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkItemQuerySortColumn {
    @JsonProperty("descending")
    private boolean descending;
    @JsonProperty("field")
    private WorkItemFieldReference field;

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }

    public WorkItemFieldReference getField() {
        return field;
    }

    public void setField(WorkItemFieldReference field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "WorkItemQuerySortColumn{" +
                "descending=" + descending +
                ", field=" + field +
                '}';
    }
}

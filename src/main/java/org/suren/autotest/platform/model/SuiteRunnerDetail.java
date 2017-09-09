package org.suren.autotest.platform.model;

public class SuiteRunnerDetail
{
    private String id;
    private String runnerId;
    private String fieldId;
    private String fieldName;
    private String action;
    private int actionOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(String runnerId) {
        this.runnerId = runnerId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getActionOrder() {
        return actionOrder;
    }

    public void setActionOrder(int actionOrder) {
        this.actionOrder = actionOrder;
    }
}
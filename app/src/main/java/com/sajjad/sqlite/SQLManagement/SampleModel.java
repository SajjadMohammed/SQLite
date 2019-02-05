package com.sajjad.sqlite.SQLManagement;

public class SampleModel {
    private int SampleId;
    private String SampleName;
    private String SampleTemp;

    public SampleModel(int sampleId, String sampleName, String sampleTemp) {
        SampleId = sampleId;
        SampleName = sampleName;
        SampleTemp = sampleTemp;
    }

    public int getSampleId() {
        return SampleId;
    }

    public void setSampleId(int sampleId) {
        SampleId = sampleId;
    }

    public String getSampleName() {
        return SampleName;
    }

    public void setSampleName(String sampleName) {
        SampleName = sampleName;
    }

    public String getSampleTemp() {
        return SampleTemp;
    }

    public void setSampleTemp(String sampleTemp) {
        SampleTemp = sampleTemp;
    }
}

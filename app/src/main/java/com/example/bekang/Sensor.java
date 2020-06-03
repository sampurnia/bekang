package com.example.bekang;

public class Sensor {
    private String data;
    private String parameter;
    private String nilai;

    public Sensor() {
    }

    public Sensor(String data, String parameter, String nilai) {
        this.data = data;
        this.parameter = parameter;
        this.nilai = nilai;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

}

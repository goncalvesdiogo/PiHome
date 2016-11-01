/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.pihome.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author 558865
 */
@ManagedBean
public class Menu {

    private MeterGaugeChartModel meterGaugeModel1;
    private String usuario;
    private ApplicationBean applicationBean;

    @PostConstruct
    public void init() {
        usuario = "Diogo";
        FacesContext context = FacesContext.getCurrentInstance();
        applicationBean = context.getApplication().evaluateExpressionGet(context, "#{applicationBean}", ApplicationBean.class);

        System.out.println(">>>>>>>>>>>>>>>>MENU ABERTO<<<<<<<<<<<<<<<");
        createGaugeModel();

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }

    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(20);
                add(30);
                add(40);
            }
        };

        List<Number> ticks = new ArrayList<Number>() {
            {
                add(10);
                add(15);
                add(20);
                add(25);
                add(30);
                add(35);
                add(40);
            }
        };

        return new MeterGaugeChartModel(10, intervals, ticks);
    }

    public void createGaugeModel() {
        usuario = "Diogo";
        System.out.println(">>>>>>>>>>>>>>>>MENU ABERTO<<<<<<<<<<<<<<<");
        meterGaugeModel1 = initMeterGaugeModel();
        meterGaugeModel1.setSeriesColors("3498DB,F39C12,E74C3C");
        //meterGaugeModel1.setGaugeLabel("°C");
        meterGaugeModel1.setGaugeLabelPosition("bottom");
        meterGaugeModel1.setMin(10);
        meterGaugeModel1.setMax(40);
        meterGaugeModel1.setShowTickLabels(true);
        meterGaugeModel1.setLabelHeightAdjust(5);
        meterGaugeModel1.setIntervalOuterRadius(26);
    }

    public void setMeterGaugeValue() {
        this.meterGaugeModel1.setValue(applicationBean.getTemperature());
    }

}

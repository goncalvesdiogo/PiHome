package com.home.pihome.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.MeterGaugeChartModel;

@ManagedBean
public class Menu {
    
    private ApplicationBean applicationBean = new ApplicationBean().getInstance();
    private MeterGaugeChartModel meterGaugeTemperature;
    private MeterGaugeChartModel meterGaugeHumidity;
    private String usuario;
    
    @PostConstruct
    public void init() {
        usuario = "Diogo";
        FacesContext context = FacesContext.getCurrentInstance();
    
        createGaugeModel();

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public MeterGaugeChartModel getMeterGaugeTemperature() {
        return meterGaugeTemperature;
    }

    public MeterGaugeChartModel getMeterGaugeHumidity() {
        return meterGaugeHumidity;
    }

    private MeterGaugeChartModel initMeterGaugeModelTemperature() {
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

    private MeterGaugeChartModel initMeterGaugeModelHumidity() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(20);
                add(40);
                add(60);
                add(80);
                add(100);
            }
        };

        List<Number> ticks = new ArrayList<Number>() {
            {
                add(0);
                add(20);
                add(40);
                add(60);
                add(80);
                add(100);
            }
        };

        return new MeterGaugeChartModel(10, intervals, ticks);
    }

    public void createGaugeModel() {
        usuario = "Diogo";
        meterGaugeTemperature = initMeterGaugeModelTemperature();
        meterGaugeTemperature.setSeriesColors("3498DB,F39C12,E74C3C");
        meterGaugeTemperature.setGaugeLabel(meterGaugeTemperature.getValue() + " °C");
        meterGaugeTemperature.setGaugeLabelPosition("bottom");
        meterGaugeTemperature.setMin(10);
        meterGaugeTemperature.setMax(40);
        meterGaugeTemperature.setShowTickLabels(true);
        meterGaugeTemperature.setLabelHeightAdjust(0);
        meterGaugeTemperature.setIntervalOuterRadius(23);

        meterGaugeHumidity = initMeterGaugeModelHumidity();
        meterGaugeHumidity.setSeriesColors("E74C3C,F39C12,3498DB,F39C12,E74C3C");
        meterGaugeHumidity.setGaugeLabel(meterGaugeHumidity.getValue() + " %");
        meterGaugeHumidity.setGaugeLabelPosition("bottom");
        meterGaugeHumidity.setMin(0);
        meterGaugeHumidity.setMax(100);
        meterGaugeHumidity.setShowTickLabels(true);
        meterGaugeHumidity.setLabelHeightAdjust(0);
        meterGaugeHumidity.setIntervalOuterRadius(23);
    }

    public void updateMeterGaugesValue() {
        updateMeterGaugeTemperature();
        updateMeterGaugeHumidity();
        this.applicationBean.updateSystemInfo();
    }

    public void updateMeterGaugeTemperature() {
        this.meterGaugeTemperature.setValue(applicationBean.getTemperature());
        this.meterGaugeTemperature.setGaugeLabel(meterGaugeTemperature.getValue() + " °C");
        
    }

    public void updateMeterGaugeHumidity() {
        this.meterGaugeHumidity.setValue(applicationBean.getHumidity());
        this.meterGaugeHumidity.setGaugeLabel(meterGaugeHumidity.getValue() + " %");
        
    }

    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public void setApplicationBean(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
    }

}

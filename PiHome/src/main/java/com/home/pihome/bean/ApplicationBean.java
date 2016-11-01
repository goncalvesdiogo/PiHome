/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.pihome.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;



@ManagedBean(eager=true)
@ApplicationScoped
public class ApplicationBean {
    
    private String texto;
    
    @PostConstruct
    public void init(){
        texto = "Hello!";
        System.out.println("TESTE");
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.pihome.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.swing.JOptionPane;

/**
 *
 * @author 558865
 */
@ManagedBean
public class Menu {
    
    private String usuario;
    
    @PostConstruct
    public void init(){
        usuario = "Diogo";      
        System.out.println(">>>>>>>>>>>>>>>>MENU ABERTO<<<<<<<<<<<<<<<");    
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}

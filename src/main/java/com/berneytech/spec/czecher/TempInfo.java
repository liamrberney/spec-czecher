/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;

import com.profesorfalken.jsensors.*;
import com.profesorfalken.jsensors.model.components.*;
import com.profesorfalken.jsensors.model.sensors.*;
import java.util.List;

/**
 *
 * @author bernelia000
 */
public class TempInfo {
    public List<String> GpuInfo; 
    public List<String> CpuInfo;
    public List<String> DiskInfo;
    public List<String> MemoryInfo;
    
    public TempInfo(){
        setCpuInfo();
    
    }

    private void setCpuInfo() {
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                if (cpu.sensors != null) {
                    //Add temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        CpuInfo.add(temp.name + ": " + temp.value + " C");
                    }

                    //Print fan speed
                    List<Fan> fans = cpu.sensors.fans;
                    for (final Fan fan : fans) {
                      CpuInfo.add(fan.name + ": " + fan.value + " RPM");
                  }
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;

import com.profesorfalken.jsensors.*;
import com.profesorfalken.jsensors.model.components.*;
import com.profesorfalken.jsensors.model.sensors.*;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bernelia000
 */
public class TempInfo {
    public List<String> GpuInfo; 
    public List<String> CpuInfo;
    public List<String> DiskInfo;
    
    public TempInfo(){
        CpuInfo= new ArrayList<>();
        setCpuInfo();
    
    }
    public List<String> getCpuInfo(){
        return CpuInfo;
    }
    public List<String> getGpuInfo(){
        return GpuInfo;
    }
    public List<String> getDiskInfo(){
        return DiskInfo;
    }

    
    private void setCpuInfo() {
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                if (cpu.sensors != null) {
                    readComponent(cpu);             
                }
            }
        }
        List<Gpu> gpus = components.gpus;
        if (gpus != null) {
            for (final Gpu gpu : gpus) {
                if (gpu.sensors != null) {
                    readComponent(gpu);             
                }
            }
        }
        List<Disk> disks = components.disks;
        if (disks != null) {
            for (final Disk disk : disks) {
                if (disk.sensors != null) {
                    readComponent(disk);             
                }
            }
        }
    }
    private static void readComponent(Component component) {
		if (component.sensors != null) {

			List<Temperature> temps = component.sensors.temperatures;
			for (final Temperature temp : temps) {
				System.out.println(temp.name + ": " + temp.value + " C");
			}

			List<Fan> fans = component.sensors.fans;
			for (final Fan fan : fans) {
				System.out.println(fan.name + ": " + fan.value + " RPM");
			}

			List<Load> loads = component.sensors.loads;
			for (final Load load : loads) {
				System.out.println(load.name + ": " + load.value);
			}
		}
	}
}

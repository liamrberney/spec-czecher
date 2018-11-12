package com.berneytech.spec.czecher;

import com.profesorfalken.jsensors.*;
import com.profesorfalken.jsensors.model.components.*;
import com.profesorfalken.jsensors.model.sensors.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liamrberney
 */
public class TempInfo {
    public List<String> GpuInfo; 
    public List<String> CpuInfo;
    public List<String> DiskInfo;
    
    public static List<List<String>> run(){
        List<List<String>> a = new ArrayList<>();
        List<String> d= new ArrayList<>();
        d.add(" ");
        TempInfo b= new TempInfo();
        a.add(d);
        a.add(b.getCpuInfo());
        a.add(b.getGpuInfo());
        a.add(b.getDiskInfo());
        return a;
    }
    public TempInfo(){
        CpuInfo= new ArrayList<>();
        setCpuInfo();
        GpuInfo= new ArrayList<>();
        setGpuInfo();
        DiskInfo= new ArrayList<>();
        setDiskInfo();
    
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
                    CpuInfo=readComponent(cpu);             
                }
            }
        }
        CpuInfo.add(0,"Cpu Info:");
    }
    private void setGpuInfo() {
        Components components = JSensors.get.components();
        List<Gpu> gpus = components.gpus;
        if (gpus != null) {
            for (final Gpu gpu : gpus) {
                if (gpu.sensors != null) {
                    GpuInfo=readComponent(gpu);             
                }
            }
        }
        GpuInfo.add(0,"Gpu Info:");
    }
    private void setDiskInfo() {
        Components components = JSensors.get.components();
        List<Disk> disks = components.disks;
        if (disks != null) {
            for (final Disk disk : disks) {
                if (disk.sensors != null) {
                    DiskInfo=readComponent(disk);             
                }
            }
        }
        DiskInfo.add(0,"Disk Info:");
    }
    private static List<String> read​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​​Component(Component component) {
                List<String> a= new ArrayList<>();
		if (component.sensors != null) {

			List<Temperature> temps = component.sensors.temperatures;
			for (final Temperature temp : temps) {
				a.add(temp.name + ": " + temp.value + " C");
			}

			List<Fan> fans = component.sensors.fans;
			for (final Fan fan : fans) {
				a.add(fan.name + ": " + fan.value + " RPM");
			}

			List<Load> loads = component.sensors.loads;
			for (final Load load : loads) {
				a.add(load.name + ": " + load.value);
			}
		}
                return a;
	}
}

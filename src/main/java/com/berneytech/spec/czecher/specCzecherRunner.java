/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;


import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author bernelia000
 */
public class specCzecherRunner {
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Oshi (https://github.com/oshi/oshi)
 *
 * Copyright (c) 2010 - 2018 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Maintainers:
 * dblock[at]dblock[dot]org
 * widdis[at]gmail[dot]com
 * enrico.bianchi[at]gmail[dot]com
 *
 * Contributors:
 * https://github.com/oshi/oshi/graphs/contributors
 */


/**
 * The Class SystemInfoTest.
 *
 * @author dblock[at]dblock[dot]org
 */

    /**
     * Test system info.
     */
    //@Test
    //public void testCentralProcessor() {
    //    assertFalse(PlatformEnum.UNKNOWN.equals(SystemInfo.getCurrentPlatformEnum()));
    //}

    /**
     * The main method, demonstrating use of classes.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        // Options: ERROR > WARN > INFO > DEBUG > TRACE
       /* System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.err");
        Logger LOG = LoggerFactory.getLogger(specCzecherRunner.class);

        LOG.info("Initializing System...");
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        System.out.println(os);

        LOG.info("Checking computer system...");
        printComputerSystem(hal.getComputerSystem());

        LOG.info("Checking Processor...");
        printProcessor(hal.getProcessor());

        LOG.info("Checking Memory...");
        printMemory(hal.getMemory());

        LOG.info("Checking CPU...");
        printCpu(hal.getProcessor());

        LOG.info("Checking Processes...");
        printProcesses(os, hal.getMemory());

        LOG.info("Checking Sensors...");
        printSensors(hal.getSensors());

        LOG.info("Checking Power sources...");
        printPowerSources(hal.getPowerSources());

        LOG.info("Checking Disks...");
        printDisks(hal.getDiskStores());

        LOG.info("Checking File System...");
        printFileSystem(os.getFileSystem());

        LOG.info("Checking Network interfaces...");
        printNetworkInterfaces(hal.getNetworkIFs());

        LOG.info("Checking Network parameterss...");
        printNetworkParameters(os.getNetworkParams());

        // hardware: displays
        LOG.info("Checking Displays...");
        printDisplays(hal.getDisplays());

        // hardware: USB devices
        LOG.info("Checking USB Devices...");
        printUsbDevices(hal.getUsbDevices(true));
        Components components = JSensors.get.components();

    List<Cpu> cpus = components.cpus;
    if (cpus != null) {
        for (final Cpu cpu : cpus) {
            System.out.println("Found CPU component: " + cpu.name);
            if (cpu.sensors != null) {
              System.out.println("Sensors: ");
  
              //Print temperatures
              List<Temperature> temps = cpu.sensors.temperatures;
              for (final Temperature temp : temps) {
                  System.out.println(temp.name + ": " + temp.value + " C");
              }
  
              //Print fan speed
              List<Fan> fans = cpu.sensors.fans;
              for (final Fan fan : fans) {
                  System.out.println(fan.name + ": " + fan.value + " RPM");
              }
            }
        }
    }
    List<Gpu> gpus = components.gpus;
    if (gpus != null) {
        for (final Gpu gpu : gpus) {
            System.out.println("Found CPU component: " + gpu.name);
            if (gpu.sensors != null) {
              System.out.println("Sensors: ");
  
              //Print temperatures
              List<Temperature> temps = gpu.sensors.temperatures;
              for (final Temperature temp : temps) {
                  System.out.println(temp.name + ": " + temp.value + " C");
              }
  
              //Print fan speed
              List<Fan> fans = gpu.sensors.fans;
              for (final Fan fan : fans) {
                  System.out.println(fan.name + ": " + fan.value + " RPM");
              }
            }
        }
    }
        */
    ExcelWriter a= new ExcelWriter();
    a.createSheet();
    }
}
    

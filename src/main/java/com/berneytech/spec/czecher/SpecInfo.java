/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.berneytech.spec.czecher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.UsbDevice;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

public class SpecInfo {
    private static List<String> printComputerSystem(final ComputerSystem computerSystem) {
        List<String> temp= new ArrayList<>();
        temp.add("System:");
        temp.add("manufacturer: " + computerSystem.getManufacturer());
        temp.add("model: " + computerSystem.getModel());
        temp.add("serialnumber: " + computerSystem.getSerialNumber());
        final Firmware firmware = computerSystem.getFirmware();
        temp.add("firmware:");
        temp.add("manufacturer: " + firmware.getManufacturer());
        temp.add("name: " + firmware.getName());
        temp.add("description: " + firmware.getDescription());
        temp.add("version: " + firmware.getVersion());
        temp.add("release date: " + (firmware.getReleaseDate() == null ? "unknown"
                : firmware.getReleaseDate() == null ? "unknown" : firmware.getReleaseDate()));
        final Baseboard baseboard = computerSystem.getBaseboard();
        temp.add("baseboard:");
        temp.add("manufacturer: " + baseboard.getManufacturer());
        temp.add("model: " + baseboard.getModel());
        temp.add("version: " + baseboard.getVersion());
        temp.add("serialnumber: " + baseboard.getSerialNumber());
        return temp;
    }

    private static List<String> printProcessor(CentralProcessor processor) {
        List<String> temp= new ArrayList<>();
        temp.add("Processor:");
        temp.add(processor.toString());
        temp.add(" " + processor.getPhysicalPackageCount() + " physical CPU package(s)");
        temp.add(" " + processor.getPhysicalProcessorCount() + " physical CPU core(s)");
        temp.add(" " + processor.getLogicalProcessorCount() + " logical CPU(s)");
        return temp;
    }

    private static List<String> printMemory(GlobalMemory memory) {
        List<String> temp= new ArrayList<>();
        temp.add("Memory: " + FormatUtil.formatBytes(memory.getAvailable()) + "/"
                + FormatUtil.formatBytes(memory.getTotal()));
        return temp;
    }

    private static List<String> printCpu(CentralProcessor processor) {
        List<String> temp= new ArrayList<>();
        temp.add("CPU Load:");
        temp.add(String.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100));
        temp.add(String.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100));
        return temp;
    }

    private static List<String> printDisks(HWDiskStore[] diskStores) {
        List<String> temp= new ArrayList<>();
        temp.add("Disks:");
        for (HWDiskStore disk : diskStores) {
            boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
           temp.add(String.format(" %s: (model: %s - S/N: %s) size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms%n",
                    disk.getName(), disk.getModel(), disk.getSerial(),
                    disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
                    readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
                    readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
                    readwrite ? disk.getTransferTime() : "?"));
            HWPartition[] partitions = disk.getPartitions();
            if (partitions == null) {
                // TODO Remove when all OS's implemented
                continue;
            }
            for (HWPartition part : partitions) {
                temp.add(String.format(" %s: %s (%s) Maj:Min=%d:%d, size: %s%s%n", part.getIdentification(),
                        part.getName(), part.getType(), part.getMajor(), part.getMinor(),
                        FormatUtil.formatBytesDecimal(part.getSize()),
                        part.getMountPoint().isEmpty() ? "" : " @ " + part.getMountPoint()));
            }
        }
        return temp;
    }

    private static List<String> printFileSystem(FileSystem fileSystem) {
       
        List<String> temp= new ArrayList<>();
        temp.add("File System:");

        temp.add(String.format("File Descriptors: %d/%d%n", fileSystem.getOpenFileDescriptors(),
                fileSystem.getMaxFileDescriptors()));

        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            temp.add(String.format(
                    " %s (%s) [%s] %s of %s free (%.1f%%) is %s "
                            + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
                            + " and is mounted at %s%n",
                    fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                    fs.getVolume(), fs.getLogicalVolume(), fs.getMount()));
        }
        return temp;
    }

    private static List<String> printNetworkInterfaces(NetworkIF[] networkIFs) {
        List<String> temp= new ArrayList<>();
            temp.add("Network Interfaces:");
        for (NetworkIF net : networkIFs) {
            temp.add(String.format("Name: %s (%s)%n", net.getName(), net.getDisplayName()));
            temp.add(String.format("MAC Address: %s %n", net.getMacaddr()));
            temp.add(String.format("MTU: %s, Speed: %s %n", net.getMTU(), FormatUtil.formatValue(net.getSpeed(), "bps")));
            temp.add(String.format("IPv4: %s %n", Arrays.toString(net.getIPv4addr())));
            temp.add(String.format("IPv6: %s %n", Arrays.toString(net.getIPv6addr())));
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                    || net.getPacketsSent() > 0;
            temp.add(String.format("   Traffic: received %s/%s%s; transmitted %s/%s%s %n",
                    hasData ? net.getPacketsRecv() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
                    hasData ? " (" + net.getInErrors() + " err)" : "",
                    hasData ? net.getPacketsSent() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?",
                    hasData ? " (" + net.getOutErrors() + " err)" : ""));
        }
        return temp;
    }

    private static List<String> printNetworkParameters(NetworkParams networkParams) {
        List<String> temp= new ArrayList<>();
        temp.add("Network parameters:");
        temp.add(String.format("Host name: %s%n", networkParams.getHostName()));
        temp.add(String.format("Domain name: %s%n", networkParams.getDomainName()));
        temp.add(String.format("DNS servers: %s%n", Arrays.toString(networkParams.getDnsServers())));
        temp.add(String.format("IPv4 Gateway: %s%n", networkParams.getIpv4DefaultGateway()));
        temp.add(String.format("IPv6 Gateway: %s%n", networkParams.getIpv6DefaultGateway()));
        return temp;
    }

    private static List<String> printDisplays(Display[] displays) {
        List<String> temp= new ArrayList<>();
        temp.add("Displays:");
        int i = 0;
        for (Display display : displays) {
            temp.add("Display " + i + ":");
            String[] a = display.toString().split(",");
            for (String b: a)
                temp.add(b);
            i++;
        }
        return temp;
    }

    private static List<String> printUsbDevices(UsbDevice[] usbDevices) {
        List<String> temp= new ArrayList<>();
        temp.add("USB Devices:");
        for (UsbDevice usbDevice : usbDevices) {
            String[] a = usbDevice.toString().split("\\|--");
            for (String b: a)
                temp.add(b);
        }
        return temp;
    }

    public static List<List<String>> run() {
        
        List<List<String>> specs = new ArrayList<>();
        
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.err");
        org.slf4j.Logger LOG = LoggerFactory.getLogger(SpecInfo.class);

        LOG.info("Initializing System...");
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        
        List<String> Temp= new ArrayList<>();
        Temp.add(os.toString());
        specs.add(Temp);
        

        LOG.info("Checking computer system...");
        specs.add(printComputerSystem(hal.getComputerSystem()));

        LOG.info("Checking Processor...");
        specs.add(printProcessor(hal.getProcessor()));

        LOG.info("Checking Memory...");
        specs.add(printMemory(hal.getMemory()));

        LOG.info("Checking CPU...");
        specs.add(printCpu(hal.getProcessor()));

        LOG.info("Checking Disks...");
        specs.add(printDisks(hal.getDiskStores()));

        LOG.info("Checking File System...");
        specs.add(printFileSystem(os.getFileSystem()));

        LOG.info("Checking Network interfaces...");
        specs.add(printNetworkInterfaces(hal.getNetworkIFs()));

        LOG.info("Checking Network parameterss...");
        specs.add(printNetworkParameters(os.getNetworkParams()));

        // hardware: displays
        LOG.info("Checking Displays...");
        specs.add(printDisplays(hal.getDisplays()));

        // hardware: USB devices
        LOG.info("Checking USB Devices...");
        specs.add(printUsbDevices(hal.getUsbDevices(true)));
 
        return specs;
    }
   
}


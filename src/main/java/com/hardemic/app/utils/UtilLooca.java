/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.utils;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;
import oshi.software.os.InternetProtocolStats;
import oshi.util.FormatUtil;

public class UtilLooca {
    private final SystemInfo si = new SystemInfo();
         
    public String getHostName(){
        return this.si.getOperatingSystem().getNetworkParams().getHostName();
    }
    
    public InternetProtocolStats getInternetProtocolStatus(){
        return this.si.getOperatingSystem().getInternetProtocolStats();
    }
    
    public List<NetworkIF> getNetorkIFs(){
        return this.si.getHardware().getNetworkIFs();
    }
    
    public void printIF(List<NetworkIF> networkIFs){
        for (NetworkIF networkIF : networkIFs) {
            System.out.println("===== Network Interface =====");
            
            boolean hasData = 
                    networkIF.getBytesRecv() > 0 || 
                    networkIF.getBytesSent() > 0 || 
                    networkIF.getPacketsRecv() > 0 || 
                    networkIF.getPacketsSent() > 0;
            
            System.out.println("NAME: " + networkIF.getDisplayName());
            System.out.println("SPEED:" + FormatUtil.formatBytes(networkIF.getSpeed()));
            
            System.out.println(
               String
                    .format("Traffic: received %s/%s%s; transmitted %s/%s%s %n",
                            
                    hasData ? networkIF.getPacketsRecv() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(networkIF.getBytesRecv()) : "?",
                    hasData ? " (" + networkIF.getInErrors() + " err)" : "",
                    
                    hasData ? networkIF.getPacketsSent() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(networkIF.getBytesSent()) : "?",
                    hasData ? " (" + networkIF.getOutErrors() + " err)" : ""
                    )
            );
        }
    }

    void printDisksStatus(Disco[] discos) {
        for (Disco disco : discos) {
            boolean readwrite = disco.getLeituras() > 0 || disco.getEscritas()> 0;
            System.out.println(
                String.format(
                    " %s: (model: %s - S/N: %s) %n"
                  + "size: %s %n"
                  + "reads: %s (%s) %n"
                  + "writes: %s (%s)%n "
                  + "xfer: %s ms %n",
                    disco.getNome(), disco.getModelo(), disco.getSerial(),
                    disco.getTamanho()> 0 ? FormatUtil.formatBytesDecimal(disco.getTamanho()) : "?",
                    readwrite ? disco.getLeituras(): "?",
                    readwrite ? FormatUtil.formatBytes(disco.getBytesDeLeitura()) : "?",
                    readwrite ? disco.getEscritas(): "?",
                    readwrite ? FormatUtil.formatBytes(disco.getBytesDeEscritas()) : "?",
                    readwrite ? disco.getTempoDeTransferencia(): "?"
                )
            );
        }
    }
    
    public Long getTamanhoVolumesDisponivel(List<Volume> volumes) {
        return volumes.stream()
                .mapToLong(volume -> volume.getDisponivel()).sum();
    }
}

package com.beatrix.creational_patterns;

import com.beatrix.creational_patterns.factories.PCFactory;
import com.beatrix.creational_patterns.factories.PCGamingFactory;
import com.beatrix.creational_patterns.factories.PCOfficeFactory;
import com.beatrix.creational_patterns.gpus.GPU;
import com.beatrix.creational_patterns.ram.RamDDR3;
import com.beatrix.creational_patterns.ram.RamDDR4;
import com.beatrix.structural_patterns.adapter.HdmiCable;
import com.beatrix.structural_patterns.adapter.Monitor;
import com.beatrix.structural_patterns.adapter.VgaAdapter;
import com.beatrix.structural_patterns.adapter.VgaCable;
import com.beatrix.structural_patterns.bridge.*;
import com.beatrix.structural_patterns.proxy.CachedVideoMemory;

import java.util.Scanner;

/**
 * @author Beatrice V.
 * @created 20.09.2020 - 18:28
 * @project CreationalPatterns
 */
public class Client {
    public static void main(String[] args) {
        Peripherals mouse = new Mouse(new RGB());
        Peripherals keyboard = new Keyboard(new Standard());

        HdmiCable hdmiCable = new HdmiCable("hdmi");
        Monitor monitor = new Monitor("hdmi");

        VgaCable vgaCable = new VgaCable("vga");
        VgaAdapter vgaAdapter = new VgaAdapter(vgaCable);

        GPU image = new CachedVideoMemory("Guns Akimbo");

        PC.getPC();
        try {
            PC.buildPC();
        } catch(NullPointerException e){
            System.err.println("You don't have a PC we can build.");
        }

        mouse.connect();
        keyboard.connect();

        if(monitor.connectViaHdmi(hdmiCable)){
            System.out.println("Hdmi connected!");
        }

        if(monitor.connectViaHdmi(vgaAdapter)){
            System.out.println("VGA cable connected via adapter to hdmi socket from monitor.");
        }

        //proxy
        image.render();
        System.out.println("");
        image.render();
    }
}

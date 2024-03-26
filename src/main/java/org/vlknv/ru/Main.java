package org.vlknv.ru;

import org.usb4java.*;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String str = "0xe851";
        int v = 59473;
        //short shrt = (short) Short.valueOf(str);

        System.out.println((short)v);

        //short vid = (short) 0xe851;
        //short pid = (short) 0xe1000;

        int vid = 59473;
        int pid = 4096;

        Context context = new Context();
        int r = LibUsb.init(context);
        if (r != LibUsb.SUCCESS) throw new LibUsbException("Unable to initialize libusb.", r);

        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(context, list);
        if (result < 0) throw new LibUsbException("Unable to get device list", result);

        //ArrayList<BarcodeScanner> scannersListFromUSB = new ArrayList<>();
        //ArrayList<Device> deviceArrayList = new ArrayList<>();
        for (Device device: list){
            DeviceDescriptor descriptor = new DeviceDescriptor();
            result = LibUsb.getDeviceDescriptor(device, descriptor);
           // System.out.println(descriptor.idVendor());
            //System.out.println(descriptor.idProduct());
            if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
            if (descriptor.idVendor() == vid && descriptor.idProduct() == pid) System.out.println("ok");
            else System.out.println("no");
        }

        LibUsb.exit(context);
    }
}
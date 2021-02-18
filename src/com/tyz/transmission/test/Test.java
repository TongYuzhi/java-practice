package com.tyz.transmission.test;

import com.tyz.transmission.files.FileInformation;
import com.tyz.transmission.files.ResourceInformation;

/**
 * @author tyz
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        ResourceInformation resourceInformation = new ResourceInformation(1, "D:\\Amusement\\");
//        resourceInformation.scanResourceFiles("aba");
        ReceivingEndTest receivingEndTest = new ReceivingEndTest(1);
        receivingEndTest.prepare();

        SendingEndTest sendingEndTest = new SendingEndTest(receivingEndTest.getResourceInformation(), receivingEndTest.getAssignments().get(0));
        sendingEndTest.prepare();
        sendingEndTest.send();
    }
}

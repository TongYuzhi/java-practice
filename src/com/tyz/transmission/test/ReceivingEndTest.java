package com.tyz.transmission.test;

import com.tyz.transmission.files.FileInformation;
import com.tyz.transmission.files.FileSplitter;
import com.tyz.transmission.files.ResourceInformation;
import com.tyz.transmission.protocol.SectionHeader;
import com.tyz.transmission.receiver.ReceivingServer;
import com.tyz.util.IPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tyz
 */
public class ReceivingEndTest implements IPublisher {
    private ResourceInformation resourceInformation;
    private int sendingEndCount;
    private List<List<SectionHeader>> assignments;

    public ReceivingEndTest(int sendingEndCount) {
        this.sendingEndCount = sendingEndCount;
    }

    public void prepare() {
        this.resourceInformation = new ResourceInformation(2,
                                "D:\\Amusement\\Useless\\drivers\\");
        try {
            this.resourceInformation.scanResourceFiles("testFiles");
            FileSplitter fileSplitter = new FileSplitter();
            List<FileInformation> fileInformations = new ArrayList<>();

            Map<Integer, FileInformation> map = this.resourceInformation.getFileInformationMap();

            for (int fileId : map.keySet()) {
                fileInformations.add(map.get(fileId));
            }

            this.assignments = fileSplitter.splitResourceFiles(fileInformations, this.sendingEndCount);
            this.resourceInformation.creatDirctories("D:\\_backup\\");
            ReceivingServer receivingServer = new ReceivingServer(sendingEndCount, resourceInformation);
            receivingServer.addPublisher(this);
            receivingServer.startUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResourceInformation getResourceInformation() {
        return resourceInformation.copyOf();
    }

    public List<List<SectionHeader>> getAssignments() {
        return assignments;
    }

    @Override
    public void dealMessage(String s) {
        System.out.println("[" + s + "]");
    }
}

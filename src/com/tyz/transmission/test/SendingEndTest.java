package com.tyz.transmission.test;

import com.tyz.transmission.files.ResourceInformation;
import com.tyz.transmission.protocol.SectionHeader;
import com.tyz.transmission.sender.SendingClient;

import java.io.IOException;
import java.util.List;

/**
 * @author tyz
 */
public class SendingEndTest {
    private ResourceInformation resourceInformation;
    private List<SectionHeader> assignments;
    private SendingClient sendingClient;

    public SendingEndTest(ResourceInformation resourceInformation, List<SectionHeader> assignments) {
        this.resourceInformation = resourceInformation;
        this.assignments = assignments;
    }

    public void prepare() {
        this.resourceInformation.setAbsolutePath("D:\\Amusement\\Useless\\drivers\\");
        this.sendingClient = new SendingClient(this.resourceInformation, this.assignments);
    }

    public void send() throws IOException {
        this.sendingClient.send();
    }
}

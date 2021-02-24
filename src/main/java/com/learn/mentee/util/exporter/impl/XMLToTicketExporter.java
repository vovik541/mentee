package com.learn.mentee.util.exporter.impl;

import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.xml.TicketXML;
import com.learn.mentee.util.converter.impl.TicketXMLToTicketConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class XMLToTicketExporter {

    @Resource
    private TicketXMLToTicketConverter ticketXMLToTicketConverter;

    @Value("${path.resources}${path.xml.tickets}")
    private String path;

    @Resource
    private Jaxb2Marshaller marshaller;

    public Ticket exportTicketFromXml() {
        File initialFile = new File(path);
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TicketXML ticketXML = (TicketXML) marshaller.unmarshal(new StreamSource(targetStream));
        return ticketXMLToTicketConverter.convert(ticketXML);
    }
}

package com.learn.mentee.xml;

import java.io.IOException;
import java.io.OutputStream;

public interface XMLBuilder<E> {
    /**
     * Build an XML file from an E object
     * @param entity E object
     * @param outputStream stream where E object is going to be exported.
     */
    void buildXml(E entity, OutputStream outputStream);
}

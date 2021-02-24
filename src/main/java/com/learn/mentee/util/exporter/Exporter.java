package com.learn.mentee.util.exporter;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public interface Exporter<O> {
    /**
     * Exports O object to an output stream.
     * @param object O generic object.
     * @param outputStream stream where O object is going to be exported.
     */
    void export(O object, OutputStream outputStream);
}

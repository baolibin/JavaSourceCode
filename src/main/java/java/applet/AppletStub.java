/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.applet;

import java.net.URL;

/**
 * When an java.applet is first created, an java.applet stub is attached to it
 * using the java.applet's <code>setStub</code> method. This stub
 * serves as the interface between the java.applet and the browser
 * environment or java.applet viewer environment in which the application
 * is running.
 *
 * @author      Arthur van Hoff
 * @see         java.applet.Applet#setStub(java.applet.AppletStub)
 * @since       JDK1.0
 */
public interface AppletStub {
    /**
     * Determines if the java.applet is active. An java.applet is active just
     * before its <code>start</code> method is called. It becomes
     * inactive just before its <code>stop</code> method is called.
     *
     * @return  <code>true</code> if the java.applet is active;
     *          <code>false</code> otherwise.
     */
    boolean isActive();


    /**
     * Gets the URL of the document in which the java.applet is embedded.
     * For example, suppose an java.applet is contained
     * within the document:
     * <blockquote><pre>
     *    http://www.oracle.com/technetwork/java/index.html
     * </pre></blockquote>
     * The document base is:
     * <blockquote><pre>
     *    http://www.oracle.com/technetwork/java/index.html
     * </pre></blockquote>
     *
     * @return  the {@link java.net.URL} of the document that contains the
     *          java.applet.
     * @see     java.applet.AppletStub#getCodeBase()
     */
    URL getDocumentBase();

    /**
     * Gets the base URL. This is the URL of the directory which contains the java.applet.
     *
     * @return  the base {@link java.net.URL} of
     *          the directory which contains the java.applet.
     * @see     java.applet.AppletStub#getDocumentBase()
     */
    URL getCodeBase();

    /**
     * Returns the value of the named parameter in the HTML tag. For
     * example, if an java.applet is specified as
     * <blockquote><pre>
     * &lt;java.applet code="Clock" width=50 height=50&gt;
     * &lt;param name=Color value="blue"&gt;
     * &lt;/java.applet&gt;
     * </pre></blockquote>
     * <p>
     * then a call to <code>getParameter("Color")</code> returns the
     * value <code>"blue"</code>.
     *
     * @param   name   a parameter name.
     * @return  the value of the named parameter,
     * or <tt>null</tt> if not set.
     */
    String getParameter(String name);

    /**
     * Returns the java.applet's context.
     *
     * @return  the java.applet's context.
     */
    AppletContext getAppletContext();

    /**
     * Called when the java.applet wants to be resized.
     *
     * @param   width    the new requested width for the java.applet.
     * @param   height   the new requested height for the java.applet.
     */
    void appletResize(int width, int height);
}

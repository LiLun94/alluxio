/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.web;

import alluxio.Constants;
import alluxio.client.file.FileSystem;
import alluxio.util.io.PathUtils;

import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.net.InetSocketAddress;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.ServletException;

/**
 * The Alluxio proxy web server.
 */
@NotThreadSafe
public final class ProxyWebServer extends WebServer {

  public static final String FILE_SYSTEM_SERVLET_RESOURCE_KEY = "File System";

  /**
   * Creates a new instance of {@link ProxyWebServer}.
   *
   * @param serviceName the service name
   * @param address the service address
   */
  public ProxyWebServer(String serviceName, InetSocketAddress address) {
    super(serviceName, address);

    // REST configuration
    ResourceConfig config = new ResourceConfig().packages("alluxio.proxy");
    ServletContainer servlet = new ServletContainer(config) {
      private static final long serialVersionUID = 7756010860672831556L;

      @Override
      public void init() throws ServletException {
        super.init();
        getServletContext()
            .setAttribute(FILE_SYSTEM_SERVLET_RESOURCE_KEY, FileSystem.Factory.get());
      }
    };
    ServletHolder servletHolder = new ServletHolder("Alluxio Proxy Web Service", servlet);
    mWebAppContext.addServlet(servletHolder, PathUtils.concatPath(Constants.REST_API_PREFIX, "*"));
  }
}
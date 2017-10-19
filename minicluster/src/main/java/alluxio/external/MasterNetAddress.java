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

package alluxio.external;

import com.google.common.base.Objects;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Network addresses for an Alluxio master.
 */
@ThreadSafe
public class MasterNetAddress {
  private final String mHostname;
  private final int mRpcPort;
  private final int mWebPort;

  public MasterNetAddress(String hostname, int rpcPort, int webPort) {
    mHostname = hostname;
    mRpcPort = rpcPort;
    mWebPort = webPort;
  }

  public String getHostname() {
    return mHostname;
  }

  public int getRpcPort() {
    return mRpcPort;
  }

  public int getWebPort() {
    return mWebPort;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("hostname", mHostname)
        .add("rpcPort", mRpcPort)
        .add("webPort", mWebPort)
        .toString();
  }
}

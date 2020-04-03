/** 
 * @fileName: Protocol.java  
 * @author: pjf
 * @date: 2020年2月28日 上午8:56:57 
 */

package com.wxhj.cloud.baidu;
/**
 * @className Protocol.java
 * @author pjf
 * @date 2020年2月28日 上午8:56:57   
*/
/** 
 * @className Protocol.java
 * @author pjf
 * @date 2020年2月28日 上午8:56:57 
*/

public enum Protocol {

    /**
     * HTTP Protocol - Using the HTTP protocol is less secure than HTTPS, but can slightly reduce the system resources
     * used when communicating with BCE.
     */
    HTTP("http", 80),

    /**
     * HTTPS Protocol - Using the HTTPS protocol is more secure than using the HTTP protocol, but may use slightly more
     * system resources. BCE recommends using HTTPS for maximize security.
     */
    HTTPS("https", 443);

    /**
     * The protocol name.
     */
    private String protocol;

    private int defaultPort;

    private Protocol(String protocol, int defaultPort) {
        this.protocol = protocol;
        this.defaultPort = defaultPort;
    }

    public int getDefaultPort() {
        return this.defaultPort;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.protocol;
    }
}

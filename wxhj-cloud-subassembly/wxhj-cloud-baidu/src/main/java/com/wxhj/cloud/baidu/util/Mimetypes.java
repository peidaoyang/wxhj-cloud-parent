/*
 * Copyright 2014 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.wxhj.cloud.baidu.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Utility class that maintains a listing of known Mimetypes, and determines the
 * mimetype of files based on file extensions.
 * <p>
 * This class is obtained with the {#link {@link #getInstance()} method that
 * recognizes loaded mime types from the file <code>mime.types</code> if this
 * file is available at the root of the classpath. The mime.types file format,
 * and most of the content, is taken from the Apache HTTP server's mime.types
 * file.
 * <p>The format for mime type setting documents is:
 * <code>mimetype &lt;Space | Tab&gt;+ extension (&lt;Space|Tab&gt;+ extension)*</code>. Any
 * blank lines in the file are ignored, as are lines starting with
 * <code>#</code> which are considered comments. Lines that have a mimetype but
 * no associated extensions are also ignored.
 */
public class Mimetypes {
    private static final Log log = LogFactory.getLog(Mimetypes.class);

    /**
     * The default binary mimetype: application/octet-stream
     */
    public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";

    private static Mimetypes mimetypes = null;

    /**
     * Map that stores file extensions as keys, and the corresponding mimetype as values.
     */
    private HashMap<String, String> extensionToMimetypeMap = new HashMap<String, String>();

    private Mimetypes() {
    }

    /**
     * Loads MIME type info from the file 'mime.types' in the classpath, if it's available.
     */
    public synchronized static Mimetypes getInstance() {
        if (mimetypes != null) {
            return mimetypes;
        }

        mimetypes = new Mimetypes();
        InputStream is = mimetypes.getClass().getResourceAsStream("/mime.types");
        if (is != null) {
            if (log.isDebugEnabled()) {
                log.debug("Loading mime types from file in the classpath: mime.types");
            }
            try {
                mimetypes.loadAndReplaceMimetypes(is);
            } catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("Failed to load mime types from file in the classpath: mime.types", e);
                }
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    log.debug("", ex);
                }
            }
        } else {
            if (log.isWarnEnabled()) {
                log.warn("Unable to find 'mime.types' file in classpath");
            }
        }
        return mimetypes;
    }

    /**
     * Reads and stores the mime type setting corresponding to a file extension, by reading
     * text from an InputStream. If a mime type setting already exists when this method is run,
     * the mime type value is replaced with the newer one.
     *
     * @param is
     * @throws java.io.IOException
     */
    public void loadAndReplaceMimetypes(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("#") || line.length() == 0) {
                // Ignore comments and empty lines.
            } else {
                StringTokenizer st = new StringTokenizer(line, " \t");
                if (st.countTokens() > 1) {
                    String mimetype = st.nextToken();
                    while (st.hasMoreTokens()) {
                        String extension = st.nextToken();
                        extensionToMimetypeMap.put(extension.toLowerCase(), mimetype);
                        if (log.isDebugEnabled()) {
                            log.debug("Setting mime type for extension '" + extension.toLowerCase() + "' to '" +
                                    mimetype + "'");
                        }
                    }
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("Ignoring mimetype with no associated file extensions: '" + line + "'");
                    }
                }
            }
        }
    }

    /**
     * Determines the mimetype of a file by looking up the file's extension in
     * an internal listing to find the corresponding mime type. If the file has
     * no extension, or the extension is not available in the listing contained
     * in this class, the default mimetype <code>application/octet-stream</code>
     * is returned.
     * <p>
     * A file extension is one or more characters that occur after the last
     * period (.) in the file's name. If a file has no extension, Guesses the
     * mimetype of file data based on the file's extension.
     *
     * @param fileName The name of the file whose extension may match a known mimetype.
     * @return The file's mimetype based on its extension, or a default value of
     * <code>application/octet-stream</code> if a mime type value cannot be found.
     */
    public String getMimetype(String fileName) {
        int lastPeriodIndex = fileName.lastIndexOf(".");
        if (lastPeriodIndex > 0 && lastPeriodIndex + 1 < fileName.length()) {
            String ext = fileName.substring(lastPeriodIndex + 1).toLowerCase();
            if (extensionToMimetypeMap.keySet().contains(ext)) {
                String mimetype = (String) extensionToMimetypeMap.get(ext);
                if (log.isDebugEnabled()) {
                    log.debug("Recognised extension '" + ext + "', mimetype is: '" + mimetype + "'");
                }
                return mimetype;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Extension '" + ext + "' is unrecognized in mime type listing" +
                            ", using default mime type: '" + MIMETYPE_OCTET_STREAM + "'");
                }
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("File name has no extension, mime type cannot be recognised for: " + fileName);
            }
        }
        return MIMETYPE_OCTET_STREAM;
    }

    /**
     * Determines the mimetype of a file by looking up the file's extension in an internal listing
     * to find the corresponding mime type. If the file has no extension, or the extension is not
     * available in the listing contained in this class, the default mimetype
     * <code>application/octet-stream</code> is returned.
     * <p>
     * A file extension is one or more characters that occur after the last period (.) in the file's name.
     * If a file has no extension,
     * Guesses the mimetype of file data based on the file's extension.
     *
     * @param file the file whose extension may match a known mimetype.
     * @return the file's mimetype based on its extension, or a default value of
     * <code>application/octet-stream</code> if a mime type value cannot be found.
     */
    public String getMimetype(File file) {
        return getMimetype(file.getName());
    }

}

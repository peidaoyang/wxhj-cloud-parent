/** 
 * @fileName: Headers.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:11:01 
 */

package com.wxhj.cloud.baidu.http;
/**
 * @className Headers.java
 * @author pjf
 * @date 2020年2月28日 上午9:11:01   
*/
/** 
 * @className Headers.java
 * @author pjf
 * @date 2020年2月28日 上午9:11:01 
*/

public interface Headers {

    /*
     * Standard HTTP Headers
     */

    public static final String AUTHORIZATION = "Authorization";

    public static final String CACHE_CONTROL = "Cache-Control";

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String CONTENT_ENCODING = "Content-Encoding";

    public static final String CONTENT_LENGTH = "Content-Length";

    public static final String CONTENT_MD5 = "Content-MD5";

    public static final String CONTENT_RANGE = "Content-Range";

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String DATE = "Date";

    public static final String ETAG = "ETag";

    public static final String EXPIRES = "Expires";

    public static final String HOST = "Host";

    public static final String LAST_MODIFIED = "Last-Modified";

    public static final String LOCATION = "Location";

    public static final String RANGE = "Range";

    public static final String SERVER = "Server";

    public static final String TRANSFER_ENCODING = "Transfer-Encoding";

    public static final String USER_AGENT = "User-Agent";


    /*
     * BCE Common HTTP Headers
     */

    public static final String BCE_ACL = "x-bce-acl";

    public static final String BCE_ACL_GRANT_READ = "x-bce-grant-read";

    public static final String BCE_ACL_GRANT_FULL_CONTROL = "x-bce-grant-full-control";

    public static final String BCE_CONTENT_SHA256 = "x-bce-content-sha256";

    public static final String BCE_COPY_METADATA_DIRECTIVE = "x-bce-metadata-directive";

    public static final String BCE_COPY_SOURCE_IF_MATCH = "x-bce-copy-source-if-match";

    public static final String BCE_DATE = "x-bce-date";

    public static final String BCE_PREFIX = "x-bce-";

    public static final String BCE_REQUEST_ID = "x-bce-request-id";

    public static final String BCE_SECURITY_TOKEN = "x-bce-security-token";

    public static final String BCE_USER_METADATA_PREFIX = "x-bce-meta-";

    public static final String BCE_CONTENT_CRC32 = "x-bce-content-crc32";

    /*
     * BOS HTTP Headers
     */

    public static final String BCE_COPY_SOURCE = "x-bce-copy-source";

    public static final String BCE_COPY_SOURCE_RANGE = "x-bce-copy-source-range";

    public static final String BCE_COPY_SOURCE_IF_MODIFIED_SINCE = "x-bce-copy-source-if-modified-since";

    public static final String BCE_COPY_SOURCE_IF_NONE_MATCH = "x-bce-copy-source-if-none-match";

    public static final String BCE_COPY_SOURCE_IF_UNMODIFIED_SINCE = "x-bce-copy-source-if-unmodified-since";

    public static final String BCE_FETCH_SOURCE = "x-bce-fetch-source";

    public static final String BCE_FETCH_MODE = "x-bce-fetch-mode";

    public static final String BCE_DEBUG_ID = "x-bce-debug-id";

    public static final String BCE_NEXT_APPEND_OFFSET = "x-bce-next-append-offset";

    public static final String BCE_OBJECT_TYPE = "x-bce-object-type";

    public static final String BCE_STORAGE_CLASS = "x-bce-storage-class";

    public static final String BCE_RESTORE_TIER = "x-bce-restore-tier";

    public static final String BCE_RESTORE_DAYS = "x-bce-restore-days";

    /*
     * CFC HTTP Headers
     */

    public static final String BCE_LOG_RESULT = "X-Bce-Log-Result";

}

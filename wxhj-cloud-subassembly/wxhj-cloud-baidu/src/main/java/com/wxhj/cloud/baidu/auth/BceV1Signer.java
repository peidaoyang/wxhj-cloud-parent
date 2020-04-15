/** 
 * @fileName: BceV1Signer.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:35:26 
 */

package com.wxhj.cloud.baidu.auth;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wxhj.cloud.baidu.BceClientException;
import com.wxhj.cloud.baidu.http.Headers;
import com.wxhj.cloud.baidu.internal.InternalRequest;
import com.wxhj.cloud.baidu.util.DateUtils;
import com.wxhj.cloud.baidu.util.HttpUtils;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * @className BceV1Signer.java
 * @author pjf
 * @date 2020年2月28日 上午10:35:26   
*/
/** 
 * @className BceV1Signer.java
 * @author pjf
 * @date 2020年2月28日 上午10:35:26 
*/

public class BceV1Signer implements Signer {

    private static final Logger logger = LoggerFactory.getLogger(BceV1Signer.class);

    private static final String BCE_AUTH_VERSION = "bce-auth-v1";
    //private static final String DEFAULT_ENCODING = "UTF-8";
    //Charset.forName(DEFAULT_ENCODING);
    private static final Charset UTF8 = Charsets.UTF_8;

    // Default headers to sign with the BCE signing protocol.
    private static final Set<String> defaultHeadersToSign = Sets.newHashSet();
    private static final Joiner headerJoiner = Joiner.on('\n');
    private static final Joiner signedHeaderStringJoiner = Joiner.on(';');

    static {
        BceV1Signer.defaultHeadersToSign.add(Headers.HOST.toLowerCase());
        BceV1Signer.defaultHeadersToSign.add(Headers.CONTENT_LENGTH.toLowerCase());
        BceV1Signer.defaultHeadersToSign.add(Headers.CONTENT_TYPE.toLowerCase());
        BceV1Signer.defaultHeadersToSign.add(Headers.CONTENT_MD5.toLowerCase());
    }

    /**
     * @see com.wxhj.cloud.baidu.auth.baidubce.auth.Signer#sign(InternalRequest, BceCredentials)
     */
    @Override
    public void sign(InternalRequest request, BceCredentials credentials) {
        this.sign(request, credentials, null);
    }

    /**
     * Sign the given request with the given set of credentials. Modifies the passed-in request to apply the signature.
     *
     * @param request     the request to sign.
     * @param credentials the credentials to sign the request with.
     * @param options     the options for signing.
     */
    @Override
    public void sign(InternalRequest request, BceCredentials credentials, SignOptions options) {
        checkNotNull(request, "request should not be null.");

        if (credentials == null) {
            return;
        }

        if (options == null) {
            if (request.getSignOptions() != null) {
                options = request.getSignOptions();
            } else {
                options = SignOptions.DEFAULT;
            }
        }

        String accessKeyId = credentials.getAccessKeyId();
        String secretAccessKey = credentials.getSecretKey();

        request.addHeader(Headers.HOST, HttpUtils.generateHostHeader(request.getUri()));
        if (credentials instanceof BceSessionCredentials) {
            request.addHeader(Headers.BCE_SECURITY_TOKEN, ((BceSessionCredentials) credentials).getSessionToken());
        }

        Date timestamp = options.getTimestamp();
        if (timestamp == null) {
            timestamp = new Date();
        }

        String authString =
                BceV1Signer.BCE_AUTH_VERSION + "/" + accessKeyId + "/"
                        + DateUtils.formatAlternateIso8601Date(timestamp) + "/" + options.getExpirationInSeconds();

        String signingKey = this.sha256Hex(secretAccessKey, authString);
        // Formatting the URL with signing protocol.
        String canonicalURI = this.getCanonicalURIPath(request.getUri().getPath());
        // Formatting the query string with signing protocol.
        String canonicalQueryString = HttpUtils.getCanonicalQueryString(request.getParameters(), true);
        // Sorted the headers should be signed from the request.
        SortedMap<String, String> headersToSign =
                this.getHeadersToSign(request.getHeaders(), options.getHeadersToSign());
        // Formatting the headers from the request based on signing protocol.
        String canonicalHeader = this.getCanonicalHeaders(headersToSign);
        String signedHeaders = "";
        if (options.getHeadersToSign() != null) {
            signedHeaders = BceV1Signer.signedHeaderStringJoiner.join(headersToSign.keySet());
            signedHeaders = signedHeaders.trim().toLowerCase();
        }

        String canonicalRequest =
                request.getHttpMethod() + "\n" + canonicalURI + "\n" + canonicalQueryString + "\n" + canonicalHeader;

        // Signing the canonical request using key with sha-256 algorithm.
        String signature = this.sha256Hex(signingKey, canonicalRequest);

        String authorizationHeader = authString + "/" + signedHeaders + "/" + signature;

        logger.debug("CanonicalRequest:{}\tAuthorization:{}", canonicalRequest.replace("\n", "[\\n]"),
                authorizationHeader);

        request.addHeader(Headers.AUTHORIZATION, authorizationHeader);
    }

    private String getCanonicalURIPath(String path) {
        if (path == null) {
            return "/";
        } else if (path.startsWith("/")) {
            return HttpUtils.normalizePath(path);
        } else {
            return "/" + HttpUtils.normalizePath(path);
        }
    }

    private String getCanonicalHeaders(SortedMap<String, String> headers) {
        if (headers.isEmpty()) {
            return "";
        }

        List<String> headerStrings = Lists.newArrayList();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            if (key == null) {
                continue;
            }
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            headerStrings.add(HttpUtils.normalize(key.trim().toLowerCase()) + ':' + HttpUtils.normalize(value.trim()));
        }
        Collections.sort(headerStrings);

        return headerJoiner.join(headerStrings);
    }

    private SortedMap<String, String> getHeadersToSign(Map<String, String> headers, Set<String> headersToSign) {
        SortedMap<String, String> ret = Maps.newTreeMap();
        if (headersToSign != null) {
            Set<String> tempSet = Sets.newHashSet();
            for (String header : headersToSign) {
                tempSet.add(header.trim().toLowerCase());
            }
            headersToSign = tempSet;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                if ((headersToSign == null && this.isDefaultHeaderToSign(key))
                        || (headersToSign != null && headersToSign.contains(key.toLowerCase())
                                && !Headers.AUTHORIZATION.equalsIgnoreCase(key))) {
                    ret.put(key, entry.getValue());
                }
            }
        }
        return ret;
    }

    private boolean isDefaultHeaderToSign(String header) {
        header = header.trim().toLowerCase();
        return header.startsWith(Headers.BCE_PREFIX) || defaultHeadersToSign.contains(header);
    }

    private String sha256Hex(String signingKey, String stringToSign) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(signingKey.getBytes(UTF8), "HmacSHA256"));
            return new String(Hex.encodeHex(mac.doFinal(stringToSign.getBytes(UTF8))));
        } catch (Exception e) {
            throw new BceClientException("Fail to generate the signature", e);
        }
    }

}

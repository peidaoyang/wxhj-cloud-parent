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
package com.wxhj.cloud.baidu.services.bos.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents the object metadata that is stored with Baidu Bos. This includes custom
 * user-supplied metadata, as well as the standard HTTP headers that Baidu Bos
 * sends and receives (Content-Length, ETag, Content-MD5, etc.).
 */
public class ObjectMetadata {

    /**
     * Custom user metadata, represented in responses with the x-bce-meta- header prefix
     */
    private Map<String, String> userMetadata = Maps.newHashMap();


    private String bceContentSha256;

    /**
     * It provide a default file name when customers download file from bos.
     */
    private String contentDisposition;

    /**
     * The contentEnding entity header is used compress the media-type.
     */
    private String contentEncoding;

    /**
     * The length of the request body.
     */
    private long contentLength = -1;

    /**
     * A Base64-encoded binary MD5 sum of the content of the request body.
     */
    private String contentMd5;

    /**
     * The MIME type of the body of the request.
     */
    private String contentType;

    /**
     * The entity tag of the URL.
     */
    private String eTag;

    private long instanceLength = -1;

    /**
     * The lastModified time of the object.
     */
    private Date lastModified;

    /**
     * Where in a full body message this partial message belongs.
     */
    private String contentRange;

    /**
     * The cacheControl is used for controlling HTTP caching.
     */
    private String cacheControl;

    /**
     * The expiration of optional object.
     */
    private String expires;

    /**
     * The offset of the append object.
     */
    private long appendOffset;

    /**
     * The objectType identifies whether the object is an appendable object
     * or a normal object.
     */
    private String objectType;

    private String storageClass;

    /**
     * The CRC value of the object. CRC(Cyclic Redundancy Check)
     */
    private String xBceCrc;

    public ObjectMetadata() {
    }

    public ObjectMetadata(ObjectMetadata other) {
        if (other.userMetadata != null) {
            this.userMetadata = Maps.newHashMap(other.userMetadata);
        }
        this.setBceContentSha256(other.getBceContentSha256());
        this.setContentDisposition(other.getContentDisposition());
        this.setContentEncoding(other.getContentEncoding());
        this.setContentLength(other.getContentLength());
        this.setContentMd5(other.getContentMd5());
        this.setContentType(other.getContentType());
        this.setETag(other.getETag());
        this.setInstanceLength(other.getInstanceLength());
        this.setLastModified(other.getLastModified());
        this.setExpires(other.getExpires());
        this.setAppendOffset(other.getAppendOffset());
        this.setObjectType(other.getObjectType());
        this.setCacheControl(other.getCacheControl());
        this.setStorageClass(other.getStorageClass());
        this.setxBceCrc(other.getxBceCrc());
    }

    /**
     * Gets the custom user-metadata for the associated object.
     *
     * @return The custom user metadata for the associated object.
     */
    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    /**
     * Sets the custom user-metadata for the associated object.
     *
     * @param userMetadata The custom user-metadata for the associated object. Note that
     * the key should not include the internal Bos HTTP header prefix.
     */
    public void setUserMetadata(Map<String, String> userMetadata) {
        checkNotNull(userMetadata, "userMetadata should not be null.");
        this.userMetadata = userMetadata;
    }

    /**
     * Adds the key value pair of custom user-metadata for the associated
     * object. If the entry in the custom user-metadata map already contains the
     * specified key, it will be replaced with these new contents.
     *
     * @param key The key for the custom user metadata entry. Note that the key
     * should not include the internal Bos HTTP header prefix.
     * @param value The value for the custom user-metadata entry.
     */
    public void addUserMetadata(String key, String value) {
        this.userMetadata.put(key, value);
    }

    /**
     * For internal use only. Returns the value of the userMetadata for the specified key.
     *
     * @param key the key of the userMetadata
     * @return the value of the userMetadata
     */
    public String getUserMetaDataOf(String key) {
        return this.userMetadata == null ? null : this.userMetadata.get(key);
    }

    /**
     * Sets the content range of object.
     *
     * @param contentRange The content range of object.
     */
    public void setContentRange(String contentRange) {
        this.contentRange = contentRange;
    }

    /**
     * Gets the content range of object.
     *
     * @return The content range of object.
     */
    public String getContentRange() {
        return contentRange;
    }

    /**
     * Gets the SHA-256 of the object content.
     *
     * @return The SHA-256 of the object content.
     */
    public String getBceContentSha256() {
        return this.bceContentSha256;
    }

    /**
     * Sets the SHA-256 of the object content.
     *
     * @param bceContentSha256 The SHA-256 of the object content.
     */
    public void setBceContentSha256(String bceContentSha256) {
        this.bceContentSha256 = bceContentSha256;
    }

    /**
     * Gets the optional Content-Disposition HTTP header, which specifies
     * presentation information for the object such as the recommended filename
     * for the object to be saved as.
     *
     * @return The value of the Content-Disposition header.
     * Returns <code>null</code> if the Content-Disposition header hasn't been set.
     */
    public String getContentDisposition() {
        return this.contentDisposition;
    }

    /**
     * Sets the optional Content-Disposition HTTP header, which specifies
     * presentational information such as the recommended filename for the
     * object to be saved as.
     *
     * @param contentDisposition The value for the Content-Disposition header.
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Gets the optional Content-Encoding HTTP header specifying what
     * content encodings have been applied to the object and what decoding
     * mechanisms must be applied in order to obtain the media-type referenced
     * by the Content-Type field.
     *
     * @return The HTTP Content-Encoding header. Returns <code>null</code> if it hasn't been set.
     */
    public String getContentEncoding() {
        return this.contentEncoding;
    }

    /**
     * Sets the optional Content-Encoding HTTP header specifying what
     * content encodings have been applied to the object and what decoding
     * mechanisms must be applied in order to obtain the media-type referenced
     * by the Content-Type field.
     *
     * @param contentEncoding The HTTP Content-Encoding header, as defined in RFC 2616.
     */
    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    /**
     * Gets the Content-Length HTTP header indicating the size of the
     * associated object in bytes.
     *
     * @return The Content-Length HTTP header indicating the size of the
     * associated object in bytes. Returns <code>null</code> if it hasn't been set yet.
     */
    public long getContentLength() {
        return this.contentLength;
    }

    /**
     * Sets the Content-Length HTTP header indicating the size of the
     * associated object in bytes.
     *
     * @param contentLength The Content-Length HTTP header indicating the size of the
     * associated object in bytes.
     */
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    /**
     * Gets the base64 encoded 128-bit MD5 digest of the associated object
     * (content - not including headers) according to RFC 1864. This data is
     * used as a message integrity check to verify that the data received by
     * Baidu Bos is the same data that the caller sent.
     *
     * @return The base64 encoded MD5 hash of the content for the associated
     * object.  Returns <code>null</code> if the MD5 hash of the content hasn't been set.
     */
    public String getContentMd5() {
        return this.contentMd5;
    }

    /**
     * Sets the base64 encoded 128-bit MD5 digest of the associated object
     * (content - not including headers) according to RFC 1864. This data is
     * used as a message integrity check to verify that the data received by
     * Baidu Bos is the same data that the caller sent. If set to null,then the
     * MD5 digest is removed from the metadata.
     *
     * @param contentMd5 The base64 encoded MD5 hash of the content for the object
     * associated with this metadata.
     */
    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    /**
     * Gets the Content-Type HTTP header, which indicates the type of content
     * stored in the associated object. The value of this header is a standard
     * MIME type.
     *
     * @return The HTTP Content-Type header, indicating the type of content
     * stored in the associated Bos object. Returns <code>null</code> if it hasn't been set.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Sets the Content-Type HTTP header indicating the type of content
     * stored in the associated object. The value of this header is a standard
     * MIME type.
     *
     * @param contentType The HTTP Content-Type header indicating the type of content
     * stored in the associated Bos object.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the hex encoded 128-bit MD5 digest of the associated object
     * according to RFC 1864. This data is used as an integrity check to verify
     * that the data received by the caller is the same data that was sent by
     * Baidu Bos.
     * <p>
     * This field represents the hex encoded 128-bit MD5 digest of an object's
     * content as calculated by Baidu Bos. The ContentMD5 field represents the
     * base64 encoded 128-bit MD5 digest as calculated on the caller's side.
     *
     * @return The hex encoded MD5 hash of the content for the associated object
     * as calculated by Baidu Bos. Returns <code>null</code> if it hasn't been set yet.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the hex encoded 128-bit MD5 digest of the associated object
     * according to RFC 1864. This data is used as an integrity check to verify
     * that the data received by the caller is the same data that was sent by
     * Baidu Bos.
     * <p>
     * This field represents the hex encoded 128-bit MD5 digest of an object's
     * content as calculated by Baidu Bos. The ContentMD5 field represents the
     * base64 encoded 128-bit MD5 digest as calculated on the caller's side.
     *
     * @param eTag The hex encoded MD5 hash of the content for the associated object
     * as calculated by Baidu Bos.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Returns the physical length of the entire object stored in Bos.
     * This is useful during, for example, a range get operation.
     *
     * @return the physical length of the entire object
     */
    public long getInstanceLength() {
        return this.instanceLength;
    }

    /**
     * Sets the physical length of the entire object stored in Bos.
     *
     * @param instanceLength the physical length of the entire object
     */
    public void setInstanceLength(long instanceLength) {
        this.instanceLength = instanceLength;
    }

    /**
     * Gets the value of the Last-Modified header, indicating the date
     * and time at which Baidu Bos last recorded a modification to the
     * associated object.
     *
     * @return The date and time at which Baidu Bos last recorded a modification
     * to the associated object. Returns <code>null</code> if
     * the Last-Modified header hasn't been set.
     */
    public Date getLastModified() {
        return this.lastModified;
    }

    /**
     * For internal use only. Sets the Last-Modified header value
     * indicating the date and time at which Baidu Bos last recorded a
     * modification to the associated object.
     *
     * @param lastModified The date and time at which Baidu Bos last recorded a
     * modification to the associated object.
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ObjectMetadata [");
        builder.append("userMetadata=").append(this.userMetadata);
        if (this.bceContentSha256 != null) {
            builder.append(", bceContentSha256=").append(this.bceContentSha256);
        }
        if (this.contentDisposition != null) {
            builder.append(", contentDisposition=").append(this.contentDisposition);
        }
        if (this.contentEncoding != null) {
            builder.append(", contentEncoding=").append(this.contentEncoding);
        }
        if (this.contentLength >= 0) {
            builder.append(", contentLength=").append(this.contentLength);
        }
        if (this.contentMd5 != null) {
            builder.append(", contentMd5=").append(this.contentMd5);
        }
        if (this.contentType != null) {
            builder.append(", contentType=").append(this.contentType);
        }
        if (this.eTag != null) {
            builder.append(", eTag=").append(this.eTag);
        }
        if (this.instanceLength >= 0) {
            builder.append(", instanceLength=").append(this.instanceLength);
        }
        if (this.lastModified != null) {
            builder.append(", lastModified=").append(this.lastModified);
        }
        if (this.cacheControl != null) {
            builder.append(", cacheControl=").append(this.cacheControl);
        }
        if (this.storageClass != null) {
            builder.append(", storageClass=").append(this.storageClass);
        }
        if (this.xBceCrc != null) {
            builder.append(", xBceCrc=").append(this.xBceCrc);
        }
        builder.append(']');
        return builder.toString();
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public long getAppendOffset() {
        return appendOffset;
    }

    public void setAppendOffset(long appendOffset) {
        this.appendOffset = appendOffset;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * Gets the crc of object.
     * @return the crc of object.
     */
    public String getxBceCrc() {
        return xBceCrc;
    }

    /**
     * Sets the crc of object.
     * @return the crc of object.
     */
    public void setxBceCrc(String xBceCrc) {
        this.xBceCrc = xBceCrc;
    }
}

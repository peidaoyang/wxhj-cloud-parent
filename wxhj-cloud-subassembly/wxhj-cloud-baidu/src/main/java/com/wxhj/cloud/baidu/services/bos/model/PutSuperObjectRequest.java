/*
 * Copyright 2014-2019 Baidu, Inc.
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

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import com.wxhj.cloud.baidu.auth.BceCredentials;

/**
 * Request object containing all the options for put Super Object to bucket.
 */
public class PutSuperObjectRequest extends GenericObjectRequest {

    /**
     * the part size when upload super object file
     */
    private long   chunkSize;

    /**
     * The num of threads in thread pool
     */
    private int    nThreads;

    /**
     * whether cancel upload super object file
     */
    private AtomicBoolean isSuperObjectUploadCanced = new AtomicBoolean(false);

    /**
     * The super object file containing the data to be uploaded to Baidu Bos.
     */
    private File file;

    /**
     * The uploadId used for complete
     */
    private String uploadId;

    /**
     * the part size when upload super object file
     */
    private static final long CHUNK_SIZE = 1024 * 1024 * 5L;

    private static final int AVAILABLEPROCESSORS = Runtime.getRuntime().availableProcessors();

    public PutSuperObjectRequest(String bucketName, String key, File file) {
        this(bucketName, key, file, CHUNK_SIZE, AVAILABLEPROCESSORS);
    }

    public PutSuperObjectRequest(String bucketName, String key, File file, long chunkSize) {
        this(bucketName, key, file, chunkSize, AVAILABLEPROCESSORS);
    }

    public PutSuperObjectRequest(String bucketName, String key, File file, int nThreads) {
        this(bucketName, key, file,  CHUNK_SIZE, nThreads);
    }

    /**
     * Constructs a new PutSuperObjectRequest object to upload a super object file and a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key  The key under which to store the new object.
     * @param file The path of the super object file to upload to Baidu Bos.
     * @param chunkSize the part size when upload super object file
     * @param nThreads the number of thread in thread pool
     */
    public PutSuperObjectRequest(String bucketName, String key, File file, long chunkSize,
                                 int nThreads) {
        super(bucketName, key);
        this.file = file;
        this.chunkSize = chunkSize;
        this.nThreads = nThreads;
    }

    public void cancel() {
        isSuperObjectUploadCanced.set(true);
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(long chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public PutSuperObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    @Override
    public PutSuperObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public PutSuperObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Gets the path and name of the file containing the data to be uploaded to Baidu Bos.
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @return The path and name of the file containing the data to be uploaded to Baidu Bos.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Sets the path and name of the file
     * containing the data to be uploaded to Baidu Bos.
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @param file The path and name of the  file containing the data to be uploaded to Baidu Bos.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Sets the file containing the data to be uploaded to Baidu Bos.
     * Returns this PutObjectRequest, enabling additional method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to
     * be uploaded to Baidu Bos; both cannot be specified.
     *
     * @param file The file containing the data to be uploaded to Baidu Bos.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    public PutSuperObjectRequest withFile(File file) {
        this.setFile(file);
        return this;
    }

    public int getnThreads() {
        return nThreads;
    }

    public void setnThreads(int nThreads) {
        this.nThreads = nThreads;
    }

    public PutSuperObjectRequest withnThreads(int nThreads) {
        this.setnThreads(nThreads);
        return this;
    }

    public AtomicBoolean getIsSuperObjectUploadCanced() {
        return isSuperObjectUploadCanced;
    }

    public void setIsSuperObjectUploadCanced(AtomicBoolean isSuperObjectUploadCanced) {
        this.isSuperObjectUploadCanced = isSuperObjectUploadCanced;
    }

    public PutSuperObjectRequest withIsSuperObjectUploadCanced(AtomicBoolean isSuperObjectUploadCanced) {
        this.setIsSuperObjectUploadCanced(isSuperObjectUploadCanced);
        return this;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public PutSuperObjectRequest withUploadId(String uploadId) {
        this.setUploadId(uploadId);
        return this;
    }
}

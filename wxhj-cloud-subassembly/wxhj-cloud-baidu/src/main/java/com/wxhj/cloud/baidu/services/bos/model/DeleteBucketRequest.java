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

import com.wxhj.cloud.baidu.auth.BceCredentials;

/**
 * Provides options for deleting a specified bucket. Baidu Bos buckets can only be deleted when empty.
 */
public class DeleteBucketRequest extends GenericBucketRequest {

    /**
     * Constructs a new DeleteBucketRequest, ready to be executed to delete the specified bucket.
     *
     * @param bucketName The name of the Baidu Bos bucket to delete.
     */
    public DeleteBucketRequest(String bucketName) {
        super(bucketName);
    }

    public DeleteBucketRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the Baidu Bos bucket to delete.
     *
     * @param bucketName The name of the Baidu Bos bucket to delete.
     */
    public DeleteBucketRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }
}

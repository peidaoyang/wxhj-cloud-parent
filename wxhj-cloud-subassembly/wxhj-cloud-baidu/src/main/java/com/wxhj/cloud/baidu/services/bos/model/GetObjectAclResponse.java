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

import java.util.List;

public class GetObjectAclResponse extends BosResponse {
    public static final int MAX_SUPPORTED_ACL_VERSION = 1;
    /**
     * The acl version..
     */
    private int version = 1;

    /**
     * The accessControlList of this specified bucket/object.
     */
    private List<Grant> accessControlList;

    /**
     * Gets the acl version.
     *
     * @return the acl version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the acl version.
     *
     * @param version the acl version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Gets the accessControlList of this bucket/object.
     *
     * @return The accessControlList of this bucket/object.
     */
    public List<Grant> getAccessControlList() {
        return this.accessControlList;
    }

    /**
     * Sets the accessControlList of this bucket/object.
     *
     * @param accessControlList The accessControlList of this bucket/object.
     */
    public void setAccessControlList(List<Grant> accessControlList) {
        this.accessControlList = accessControlList;
    }

}
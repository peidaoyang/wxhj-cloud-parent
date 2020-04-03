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

import com.wxhj.cloud.baidu.BceResponseMetadata;

public class BosResponseMetadata extends BceResponseMetadata {

    private String debugId;

    private Long nextAppendOffset;

    public String getBosDebugId() {
        return this.debugId;
    }

    public void setBosDebugId(String debugId) {
        this.debugId = debugId;
    }

    public Long getNextAppendOffset() {
        return nextAppendOffset;
    }

    public void setNextAppendOffset(Long nextAppendOffset) {
        this.nextAppendOffset = nextAppendOffset;
    }


}

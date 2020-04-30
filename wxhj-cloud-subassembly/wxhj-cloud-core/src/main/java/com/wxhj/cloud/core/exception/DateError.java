package com.wxhj.cloud.core.exception;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;

public class DateError extends RuntimeException{

    private static final long serialVersionUID = 6029257377054777937L;

    public DateError(WebResponseState webResponseState) {
        super(webResponseState.getStandardMessage());
    }
}

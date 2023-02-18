/*
 * Project Name: standard-code-base-trunk
 * File Name: EBizError.java
 * Class Name: EBizError
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.erget.chatgpt.enums;

import com.erget.chatgpt.exception.DisplayableError;


import java.util.Arrays;



/**
 * Class Name: EBizError Description: business errors which may be recoverable, or should be displayed on web page.
 *
 * @author SC
 */
public enum EErrorCode implements DisplayableError {
    // Default error
    COMM_INTERNAL_ERROR("COMM0001");



    private String errorCode;

    // this field is only for display, do not set it if it is not needed.
    private String displayMsg;

    private Object[] args;

    private static final String DEFAULT_ERROR_MSG = "error.common.unknown";

    EErrorCode(String errorCode, String displayMsg) {
        this.errorCode = errorCode;
        this.displayMsg = displayMsg;
    }

    EErrorCode(String errorCode, String displayMsg, String[] args) {
        this.errorCode = errorCode;
        this.displayMsg = displayMsg;
        if (args != null) {
            this.args = Arrays.copyOf(args, args.length);
        }
    }

    EErrorCode(String errorCode) {
        this.errorCode = errorCode;
        this.displayMsg = DEFAULT_ERROR_MSG;
    }

    @Override
    public boolean isBizError() {
        return !displayMsg.equals(DEFAULT_ERROR_MSG);
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getDisplayMsg() {
        return displayMsg;
    }

    public void setDisplayMsg(String displayMsg) {
        this.displayMsg = displayMsg;
    }

    /**
     * @return return the value of the var args
     */
    @Override
    public Object[] getArgs() {
        return args;
    }

    /**
     * set dynamic args for the message template
     *
     * @param args Set args value
     */
    public DisplayableError setArgs(Object... args) {
        if (args != null) {
            this.args = Arrays.copyOf(args, args.length);
        }
        return this;
    }

}

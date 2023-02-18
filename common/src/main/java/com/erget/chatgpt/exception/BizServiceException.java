
/*
* Project Name: standard-code-base-trunk
* File Name: BizServiceException.java
* Class Name: BizServiceException
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

package com.erget.chatgpt.exception;



/**
 * Base exception class for services
 *
 * @author SC
 *
 */
public class BizServiceException extends BaseException {
    private static final long serialVersionUID = 1L;


    /**
    * BizServiceException Constructor
    *
    */
    public BizServiceException(){
        super();
    }


    /**
    * BizServiceException Constructor
    *
    * @param error
    */
    public BizServiceException(DisplayableError error){
        super(error);
    }


    /**
    * BizServiceException Constructor
    *
    * @param error
    * @param message
    */
    public BizServiceException(DisplayableError error, String message){
        super(error, message);
    }


    /**
    * BizServiceException Constructor
    *
    * @param error
    * @param message
    * @param cause
    */
    public BizServiceException(DisplayableError error, String message, Throwable cause){
        super(error, message, cause);
    }
}

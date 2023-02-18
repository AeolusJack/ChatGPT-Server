
/*
* Project Name: standard-code-base-trunk
* File Name: DisplayableError.java
* Class Name: DisplayableError
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
 * Class Name: DisplayableError
 * @author SC
 *
 */
public interface DisplayableError {
    
    /**
    * Description: get the code of the error
    *
    * @return
    */
    String getErrorCode();

    
    /**
    * Description: get the text message of the error
    *
    * @return
    */
    String getDisplayMsg();

    
    /**
    * Description: get additional information to display for the error
    *
    * @return
    */
    Object[] getArgs();

    
    /**
    * Description: whether this is a business exception
    *
    * @return
    */
    boolean isBizError();
}

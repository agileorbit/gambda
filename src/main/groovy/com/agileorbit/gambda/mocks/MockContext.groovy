/* Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agileorbit.gambda.mocks

import com.amazonaws.services.lambda.runtime.ClientContext
import com.amazonaws.services.lambda.runtime.CognitoIdentity
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger

import java.util.Timer
import java.util.TimerTask

class MockContext implements Context {

    String awsRequestId
    String logGroupName
    String logStreamName
    String functionName
    CognitoIdentity identity
    ClientContext clientContext
    int memoryLimitInMB
    LambdaLogger logger

    int remainingTimeInMillis
    boolean remainingTimeDoesCountdown = false
    Timer timer = null

    @Override
    String getAwsRequestId() {
        return awsRequestId
    }

    @Override
    String getLogGroupName() {
        return logGroupName
    }

    @Override
    String getLogStreamName() {
        return logStreamName
    }

    @Override
    String getFunctionName() {
        return functionName
    }

    @Override
    String getFunctionVersion() {
        return null
    }

    @Override
    String getInvokedFunctionArn() {
        return null
    }

    @Override
    CognitoIdentity getIdentity() {
        return identity
    }

    @Override
    ClientContext getClientContext() {
        return clientContext
    }

    @Override
    int getRemainingTimeInMillis() {
        return remainingTimeInMillis
    }

    @Override
    int getMemoryLimitInMB() {
        return memoryLimitInMB
    }

    @Override
    LambdaLogger getLogger() {
        return logger
    }

    void setRemainingTimeInMillis(int remainingTimeDoesCountdown) {
        this.remainingTimeDoesCountdown = remainingTimeDoesCountdown

        if (this.remainingTimeDoesCountdown) {
            timer = new Timer()
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                void run() {
                    if (MockContext.this.remainingTimeInMillis >= 1000) {
                        MockContext.this.remainingTimeInMillis -= 1000
                    } else {
                        MockContext.this.remainingTimeInMillis = 0
                        timer.cancel()
                    }
                }
            }, 0, 1000)
        }
    }
}

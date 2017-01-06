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
import com.amazonaws.services.lambda.runtime.LambdaLogger

import java.util.UUID

class MockContextBuilder {
    private final String functionName
    private final String awsRequestId
    private final LambdaLogger logger

    private String logGroupName
    private String logStreamName
    private CognitoIdentity identity
    private ClientContext clientContext
    private int remainingTimeInMillis = 0
    private int memoryLimitInMB = 0
    private boolean remainingTimeDoesCountdown

    MockContextBuilder(String functionName) {
        this.functionName = functionName
        this.awsRequestId = UUID.randomUUID().toString()
        // By default logger just prints to System.out
        this.logger = System.out.&println
    }

    MockContextBuilder(String functionName, LambdaLogger logger) {
        this.functionName = functionName
        this.logger = logger
        this.awsRequestId = UUID.randomUUID().toString()
    }

    MockContextBuilder withLogGroupName(String logGroupName) {
        this.logGroupName = logGroupName
        this
    }

    MockContextBuilder withLogStreamName(String logStreamName) {
        this.logStreamName = logStreamName
        return this
    }

    MockContextBuilder withIdentity(CognitoIdentity identity) {
        this.identity = identity
        this
    }

    MockContextBuilder withClientContext(ClientContext clientContext) {
        this.clientContext = clientContext
        return this
    }

    MockContextBuilder withRemainingTimeInMillis(int remainingTimeInMillis) {
        this.remainingTimeInMillis = remainingTimeInMillis
        this
    }

    MockContextBuilder withMemoryLimitInMB(int memoryLimitInMB) {
        this.memoryLimitInMB = memoryLimitInMB
        this
    }

    MockContextBuilder withRemainingTimeCountdown(boolean remainingTimeDoesCountdown) {
        this.remainingTimeDoesCountdown = remainingTimeDoesCountdown
        this
    }

    MockContext build() {
        return new MockContext(
                awsRequestId: awsRequestId,
                logGroupName: logGroupName,
                logStreamName: logStreamName,
                functionName: functionName,
                identity: identity,
                clientContext: clientContext,
                remainingTimeInMillis: remainingTimeInMillis,
                memoryLimitInMB: memoryLimitInMB,
                logger: logger,
                remainingTimeDoesCountdown: remainingTimeDoesCountdown)
    }
}

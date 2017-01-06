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

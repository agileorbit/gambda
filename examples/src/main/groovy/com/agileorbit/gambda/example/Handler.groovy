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
package com.agileorbit.gambda.example

import com.agileorbit.gambda.ApiGatewayHandler
import com.agileorbit.gambda.ApiGatewayRequest
import com.agileorbit.gambda.ApiGatewayProxyResponse

import com.amazonaws.services.lambda.runtime.Context

class Handler implements ApiGatewayHandler {

    ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
        log.info("request: $request")
        def name = request.queryStringParameters?.name
        def reply = "Hello " + name

        if(name) {
            success reply
        } else {
            error "No name provided!"
        }
    }

    ApiGatewayProxyResponse handleRequestJson(ApiGatewayRequest request, Context context) {
        log.info("request: $request")
        def name = request.queryStringParameters?.name

        if(name) {
            success([greeting: "Hello $name"])
        } else {
            error([error: "No name provided!"])
        }
    }

    ApiGatewayProxyResponse statusAndHeaders(ApiGatewayRequest request, Context context) {
        log.info("request: $request")

        if(request.queryStringParameters?.teapot) {
            success([greeting: "Im a teapot!"], 418)
        } else {
            error([error: "Not a teapot!"], 406, [foo:'bar'])
        }
    }

    ApiGatewayProxyResponse timeOut(ApiGatewayRequest request, Context context) {
        log.info("request: $request")
        log.debug(context.remainingTimeInMillis.toString())
        sleep 1
        log.debug(context.remainingTimeInMillis.toString())

        success([time: context.remainingTimeInMillis])
    }
}

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
package com.agileorbit.gambda

import groovy.json.JsonBuilder
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.apache.log4j.Logger

trait ApiGatewayHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {
    static final Logger log = Logger.getLogger(this.simpleName)

    ApiGatewayProxyResponse success(String body, Integer statusCode = 200, Map headers = null) {
        new ApiGatewayProxyResponse(statusCode: statusCode, headers: headers, body: body)
    }

    ApiGatewayProxyResponse success(Map body, Integer statusCode = 200, Map headers = ["Content-Type": "application/json"]) {
        new ApiGatewayProxyResponse(statusCode: statusCode, headers: headers, body: new JsonBuilder(body).toString())
    }

    ApiGatewayProxyResponse error(String body, Integer statusCode = 400, Map headers = null) {
        new ApiGatewayProxyResponse(statusCode: statusCode, headers: headers, body: body)
    }

    ApiGatewayProxyResponse error(Map body, Integer statusCode = 400, Map headers = ["Content-Type": "application/json"]) {
        new ApiGatewayProxyResponse(statusCode: statusCode, headers: headers, body: new JsonBuilder(body).toString())
    }
}

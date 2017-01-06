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

import groovy.json.JsonSlurper
import groovy.transform.ToString

/**
 * http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html#api-gateway-simple-proxy-for-lambda-input-format
 */
@ToString(includeFields = true)
class ApiGatewayRequest {
    String resource
    String path
    String httpMethod
    Map<String, String> headers
    Map<String, String> queryStringParameters
    Map<String, String> pathParameters
    Map<String, String> stageVariables
    RequestContext requestContext
    String body
    Boolean isBase64Encoded

    Map getBody() {
        body ? new JsonSlurper().parseText(body) : [:]
    }
}

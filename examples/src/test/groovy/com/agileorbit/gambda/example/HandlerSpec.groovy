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

import com.amazonaws.services.lambda.runtime.Context
import com.agileorbit.gambda.ApiGatewayProxyResponse
import com.agileorbit.gambda.ApiGatewayRequest
import com.agileorbit.gambda.mocks.MockContextBuilder
import spock.lang.Shared
import spock.lang.Specification

class HandlerSpec extends Specification {
    @Shared Context ctx = new MockContextBuilder('hello-world')
            .withRemainingTimeInMillis(2000)
            .build()
    @Shared Handler handler = new Handler()

    def "correct response for name String"() {
        when:
        ApiGatewayRequest request = new ApiGatewayRequest(queryStringParameters: [name: 'Bobby'])
        ApiGatewayProxyResponse response = handler.handleRequest(request, ctx)

        then:
        with(response) {
            body == 'Hello Bobby'
            statusCode == 200
        }
    }

    def "error response for name String"() {
        when:
        ApiGatewayRequest request = new ApiGatewayRequest()
        ApiGatewayProxyResponse response = handler.handleRequest(request, ctx)

        then:
        with(response) {
            body == 'No name provided!'
            statusCode == 400
        }
    }

    def "correct response for name JSON"() {
        when:
        ApiGatewayRequest request = new ApiGatewayRequest(queryStringParameters: [name: 'Bobby'])
        ApiGatewayProxyResponse response = handler.handleRequestJson(request, ctx)

        then:
        with(response) {
            body == '{"greeting":"Hello Bobby"}'
            statusCode == 200
        }
    }

    def "error response for name JSON"() {
        when:
        ApiGatewayRequest request = new ApiGatewayRequest()
        ApiGatewayProxyResponse response = handler.handleRequestJson(request, ctx)

        then:
        with(response) {
            body == '{"error":"No name provided!"}'
            statusCode == 400
        }
    }

    def "success and headers response for name JSON"() {
        when:
        ApiGatewayRequest request = new ApiGatewayRequest(queryStringParameters: [teapot:"true"])
        ApiGatewayProxyResponse response = handler.statusAndHeaders(request, ctx)

        then:
        with(response) {
            body == '{"greeting":"Im a teapot!"}'
            statusCode == 418
            headers == ["Content-Type": "application/json"]
        }
    }

    def "error and headers response for name JSON"() {
        when:
        ApiGatewayRequest request = new ApiGatewayRequest()
        ApiGatewayProxyResponse response = handler.statusAndHeaders(request, ctx)

        then:
        with(response) {
            body == '{"error":"Not a teapot!"}'
            statusCode == 406
            headers == [foo: "bar"]
        }
    }

}

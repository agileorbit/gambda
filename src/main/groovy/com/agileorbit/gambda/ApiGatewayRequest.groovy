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

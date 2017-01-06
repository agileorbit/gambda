package com.agileorbit.gambda

import groovy.transform.ToString

/**
 * https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html#api-gateway-simple-proxy-for-lambda-output-format
 */
@ToString(includeFields = true)
class ApiGatewayProxyResponse {
    Integer statusCode
    Map<String, String> headers
    String body
}

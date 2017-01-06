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

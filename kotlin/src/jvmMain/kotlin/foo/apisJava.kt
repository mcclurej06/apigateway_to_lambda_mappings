package foo

import com.amazonaws.services.apigateway.AmazonApiGatewayClientBuilder
import com.amazonaws.services.apigateway.model.GetRestApisRequest

val client = AmazonApiGatewayClientBuilder.defaultClient()

actual fun getRestApis(): List<RestApi> {
    return client.getRestApis(GetRestApisRequest()).items.map { RestApi(it.id, it.name) }
}

actual fun getResources(getResourcesRequest: GetResourcesRequest): List<Resource> {
    return client.getResources(com.amazonaws.services.apigateway.model.GetResourcesRequest().apply {
        restApiId = getResourcesRequest.restApiId
        limit = getResourcesRequest.limit
    }).items.map { Resource(it.id, it.path) }
}

actual fun getIntegration(getIntegrationRequest: GetIntegrationRequest): Integration {
    val integration = client.getIntegration(com.amazonaws.services.apigateway.model.GetIntegrationRequest().apply {
        resourceId = getIntegrationRequest.resourceId
        restApiId = getIntegrationRequest.restApiId
        httpMethod = getIntegrationRequest.httpMethod
    })

    return Integration(integration.uri)
}
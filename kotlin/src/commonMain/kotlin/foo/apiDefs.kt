package foo

expect fun getRestApis(): List<RestApi>
expect fun getResources(getResourcesRequest: GetResourcesRequest): List<Resource>
expect fun getIntegration(getIntegrationRequest: GetIntegrationRequest): Integration

data class RestApi(
        val id: String,
        val name: String
)

data class GetResourcesRequest(
        val restApiId: String,
        val limit: Int
)

data class Resource(
        val id: String,
val path:String
)

data class Integration(
        val uri: String?
)

data class GetIntegrationRequest(
        val restApiId: String,
        val resourceId: String,
        val httpMethod: String
)
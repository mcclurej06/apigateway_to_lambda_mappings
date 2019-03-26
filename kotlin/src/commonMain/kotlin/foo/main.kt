package foo

val methods = listOf("GET", "POST", "DELETE", "PUT", "PATCH")

fun go() {
    val data = mutableMapOf<String, MutableMap<String, MutableList<String>>>()


    getRestApis().forEach { api: RestApi ->
        val apiData = mutableMapOf<String, MutableList<String>>()
        val getResourcesRequest = GetResourcesRequest(api.id, 7777)
        getResources(getResourcesRequest).forEach { resource ->
            methods.forEach { method ->
                val getIntegrationRequest = GetIntegrationRequest(api.id, resource.id, method)
                try {
                    val integration = getIntegration(getIntegrationRequest)
                    if (integration.uri?.contains("lambda") == true) {
                        val lambdaFunction = integration.uri.split(':').last().split('/').first()
                        if (!apiData.containsKey(lambdaFunction)) {
                            apiData[lambdaFunction] = mutableListOf()
                        }
                        val thing = "$method, ${resource.path}"
                        println("adding $thing")
                        apiData[lambdaFunction]?.add(thing)
                    }
                } catch (e: Exception) {
                    
                }
            }
        }
        data[api.name] = apiData
    }
    println(serialize(data))
    serialize(data)
}

expect fun serialize(data: MutableMap<String, MutableMap<String, MutableList<String>>>): String
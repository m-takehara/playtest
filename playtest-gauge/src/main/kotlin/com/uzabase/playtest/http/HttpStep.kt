package com.uzabase.playtest.http

import com.thoughtworks.gauge.Step
import com.uzabase.JsonNode
import com.uzabase.playtest.*
import com.uzabase.playtest.GaugeRestConfig
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain

class HttpStep {
    @Step("URL<url>にGETリクエストを送る")
    fun executeGet(url: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executeGet(endpoint)
        DataStore.storeStatusCodeToScenario(statusCode)
        DataStore.storeResponseHeadersToScenario(headers)
        DataStore.storeResponseBodyToScenario(body)
    }

    @Step("HTTPレスポンスステータスコードが<statusCode>である")
    fun assertStatusCodeEquals(statusCode: Int) {
        DataStore.loadStatusCodeFromScenario() shouldBeEqualTo statusCode
    }

    @Step("レスポンスヘッダーに<key>が存在し、その値が<value>である")
    fun assertResponseHeadersContain(key: String, value: String) {
        val headers = DataStore.loadResponseHeadersFromScenario()
        headers shouldContain Pair(key, value)
    }

    @Step("JSON形式のレスポンスボディが<jsonBody>と一致する")
    fun assertJsonResponseBodyEquals(jsonBody: String) {
        val expected = JsonNode.of(jsonBody).toJsonString()
        val actual = DataStore.loadResponseBodyFromScenario().string.let { JsonNode.of(it) }.toJsonString()
        actual shouldBeEqualTo expected
    }

    private fun getUrl(): String {
        val protocol = GaugeRestConfig.get(ConfigKeys.URL_PROTOCOL)
        val domain = GaugeRestConfig.get(ConfigKeys.URL_DOMAIN)
        val port = GaugeRestConfig.get(ConfigKeys.URL_PORT)
        return "$protocol://$domain:$port"
    }
}

package com.nttdata.demoanvil.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class QueryInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val url = originalUrl.newBuilder()
            .addQueryParameter("apiKey", "ce3512db")
            .build()


        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)

    }
}
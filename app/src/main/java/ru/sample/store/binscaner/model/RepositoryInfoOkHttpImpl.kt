package ru.sample.store.binscaner.model

import com.google.gson.Gson
import okhttp3.*
import ru.sample.store.binscaner.BINLIST_API_KEY
import ru.sample.store.binscaner.BIN_URL
import ru.sample.store.binscaner.FAILURE_EX
import ru.sample.store.binscaner.model.dto.BinDTO
import java.io.IOException

class RepositoryInfoOkHttpImpl : RepositoryInfo{

    override fun getInfo(number: Int, callback: MainCallback) {

        val client = OkHttpClient()
        val builder = Request.Builder()
        builder.addHeader(BINLIST_API_KEY, "")
        builder.url("$BIN_URL${number}")
        val request: Request = builder.build()
        val call: Call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    response.body?.let {
                        val responseString = it.string()
                        val binDTO =
                            Gson().fromJson((responseString), BinDTO::class.java)
                        callback.onResponse(binDTO)
                    }
                } else {
                    callback.onFailure(IOException(FAILURE_EX))
                }
            }

        })
    }
}
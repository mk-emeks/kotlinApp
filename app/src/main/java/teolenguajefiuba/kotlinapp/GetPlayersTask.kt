package teolenguajefiuba.kotlinapp

import android.os.AsyncTask
import android.widget.TextView
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetPlayersTask(textView: TextView) : AsyncTask<Void, Void, String>() {

    val innerTextView: TextView? = textView

    override fun doInBackground(vararg params: Void?): String? {
        //val url = URL("https://raw.githubusercontent.com/irontec/android-kotlin-samples/master/common-data/bilbao.json")
        // val url = URL("https://10.0.2.2:8080")
        val url = URL("http://192.168.43.82:8080/")
        val httpClient = url.openConnection() as HttpURLConnection
        if(httpClient.getResponseCode() == HttpURLConnection.HTTP_OK){
            try {
                val stream = BufferedInputStream(httpClient.getInputStream())
                val data: String = readStream(inputStream = stream)
                return data;
            } catch (e : Exception) {
                e.printStackTrace()
            } finally {
                httpClient.disconnect()
            }
        }else{
            println("ERROR ${httpClient.getResponseCode()}")
        }
        return null
    }

    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream));
        val stringBuilder = StringBuilder();
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        innerTextView?.setText(result)
        //innerTextView?.setText(JSONObject(result).toString())

        /**
         * ... Work with the weather data
         */

    }
}
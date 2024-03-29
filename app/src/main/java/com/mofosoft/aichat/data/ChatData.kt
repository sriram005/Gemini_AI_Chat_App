package com.mofosoft.aichat.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import com.mofosoft.aichat.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {
    val api_key  = BuildConfig.API_KEY

    suspend fun getResponse(prompt : String) : Chat {
        val generativemodel = GenerativeModel(
            modelName = "gemini-pro", apiKey = api_key
        )

        try{
            val response = withContext(Dispatchers.IO){
                generativemodel.generateContent(prompt)
            }
            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }catch (e : Exception){
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }


    suspend fun getResponse(prompt : String, bitmap : Bitmap) : Chat {
        val generativemodel = GenerativeModel(
            modelName = "gemini-pro-vision", apiKey = api_key
        )

        try{

            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = withContext(Dispatchers.IO){
                generativemodel.generateContent(inputContent)
            }
            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }catch (e : Exception){
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }
}
package com.mofosoft.aichat

import android.graphics.Bitmap
import com.mofosoft.aichat.data.Chat

data class ChatState(
    val chatList : MutableList<Chat>  = mutableListOf(),
    val prompt : String = "",
    val bitmap : Bitmap? = null
)

package com.pureKnowledge.salesApp.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

// Function to copy text to the clipboard
fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("text", text)
    clipboard.setPrimaryClip(clip)
}
package com.rafaltrzcinski.godtnoandroid.utils

import android.os.Build
import android.text.Html


@Suppress("deprecation")
fun String.loadHtmlText(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        else Html.fromHtml(this).toString()
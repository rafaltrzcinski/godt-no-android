package com.rafaltrzcinski.godtnoandroid.utils

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.loadImageFromUrl(url: String) =
        Picasso.get()
                .load(Uri.parse(url))
                .into(this)
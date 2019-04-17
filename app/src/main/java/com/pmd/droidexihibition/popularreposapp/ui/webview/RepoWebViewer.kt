package com.pmd.droidexihibition.popularreposapp.ui.webview

import android.content.Context
import android.net.Uri

interface RepoWebViewer {
    fun showInWebView(appContext: Context, uri: Uri)
}
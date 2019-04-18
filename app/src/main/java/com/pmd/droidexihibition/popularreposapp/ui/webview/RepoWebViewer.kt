package com.pmd.droidexihibition.popularreposapp.ui.webview

import android.content.Context
import android.net.Uri

interface RepoWebViewer {
    fun showInWebView(activityContext: Context, uri: Uri)
}
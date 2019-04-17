package com.pmd.droidexihibition.popularreposapp.ui.webview

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

class ChromeTabViewer : RepoWebViewer {
    override fun showInWebView(appContext: Context, uri: Uri) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(appContext, uri)
    }
}
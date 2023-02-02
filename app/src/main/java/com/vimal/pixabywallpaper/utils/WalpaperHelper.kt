package com.vimal.pixabywallpaper.utils

import android.app.WallpaperManager
import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.widget.Toast
import java.io.IOException
import java.net.URL

object WallpaperHelper {

    fun setHomeWallpaper(context: Context, linkImage: String): Boolean {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val wallpaperManager = WallpaperManager.getInstance(context)
        return try {
            val ins = URL(linkImage).openStream()
            wallpaperManager.setStream(ins)
            Toast.makeText(context,"Wallpapers Set",Toast.LENGTH_SHORT).show()
            true
        } catch (e: IOException) {
            Toast.makeText(context,"Not supported",Toast.LENGTH_SHORT).show()

            e.printStackTrace()
            false
        }
    }

    fun setLockScreenWallpaper(context: Context, linkImage: String): Boolean {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val wallpaperManager = WallpaperManager.getInstance(context)
        try {
            val ins = URL(linkImage).openStream()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setStream(ins, null, true, WallpaperManager.FLAG_LOCK)
            } else {
                Toast.makeText(context,"Not supported",Toast.LENGTH_SHORT).show()

            }
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    fun setHomeLockWallpaper(context: Context, linkImage: String): Boolean {
        return try {
            setHomeWallpaper(context, linkImage)
            setLockScreenWallpaper(context, linkImage)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

}
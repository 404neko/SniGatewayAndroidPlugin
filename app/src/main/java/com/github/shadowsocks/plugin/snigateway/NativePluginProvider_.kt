package com.github.shadowsocks.plugin.snigateway

import android.net.Uri
import android.os.ParcelFileDescriptor
import com.github.shadowsocks.plugin.NativePluginProvider
import com.github.shadowsocks.plugin.PathProvider
import java.io.File
import java.io.FileNotFoundException

/**
 * Created by Alex on 2018/1/31.
 */
final class NativePluginProvider_ : NativePluginProvider() {

    override fun openFile(uri: Uri?): ParcelFileDescriptor {
        var path = uri?.getPath()
        if (path==null)
            throw FileNotFoundException()
        return ParcelFileDescriptor.open(File(getExecutable()), ParcelFileDescriptor.MODE_READ_ONLY)

    }

    override fun getExecutable(): String{
        return getContext().getApplicationInfo().nativeLibraryDir + "/libsnigateway.so"
    }

    override fun populateFiles(provider: PathProvider) {
         provider.addPath("snigateway", "755")
    }

}
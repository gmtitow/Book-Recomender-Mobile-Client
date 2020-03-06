package c.tgm.booksapplication.any;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.ResponseBody;

public class Utils {
    public static void saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            String test = "http://yabs.yandex.ru/resource/SFCTnritCWB2pME4puotRQ.png";
            in = new BufferedInputStream(new URL(test).openStream());
            fout = new FileOutputStream(filename);
            
            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }
    
    public static boolean writeResponseBodyToDisk(ResponseBody body, File outputFile) {
        try {
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                byte[] fileReader = new byte[4096];
                
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(outputFile);
                
                while (true) {
                    int read = inputStream.read(fileReader);
                    
                    if (read == -1) {
                        break;
                    }
                    
                    outputStream.write(fileReader, 0, read);
                    
                    fileSizeDownloaded += read;
                    
//                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                
                outputStream.flush();
                
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}


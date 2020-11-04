package com.actualizer.system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader {

    private static final int BUFFER_SIZE = 4096;

    public static boolean download(String httpUser, String httpPassword, String downloadUrl, String stageFileName){

        boolean isDownloaded = false;

        URL url = null;
        try {

            url = new URL(downloadUrl);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(stageFileName);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];

            long downloadedFileSize = 0;

            while((bytesRead = inputStream.read(buffer)) != -1){

                downloadedFileSize += bytesRead;

                fileOutputStream.write(buffer, 0, bytesRead);

                System.out.println(downloadedFileSize + "   bytes");





            }

            fileOutputStream.close();
            inputStream.close();

            isDownloaded = true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            isDownloaded = false;

        } catch (IOException e) {
            e.printStackTrace();

            isDownloaded = false;
        }

        return isDownloaded;

    }

}

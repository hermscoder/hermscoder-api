package com.herms.hermscoder.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertUtils {

    private ConvertUtils(){}

    public static LocalDate stringToLocalDate(String dateStr) {
        if(!Utils.isEmptyOrNull(dateStr)) {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        }

        return null;
    }

    public static String localDateToString(LocalDate date) {
        if(date != null) {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        }
        return null;
    }

    public static MultipartFile convertUrlToMultipartFile(String urlStr){

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MultipartFile resultFile = null;
        Image image = null;
        try{
            URL url = new URL(urlStr);
            image = ImageIO.read(url);
            InputStream in = new BufferedInputStream(url.openStream());

            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] fileBytes = out.toByteArray();
            resultFile = new MockMultipartFile("test",fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFile;
    }

}

package com.programmers.coffeespring.util;

import com.programmers.coffeespring.exception.CafeException;
import com.programmers.coffeespring.exception.ErrorCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImgUtil {
    private static final String IMG_PATH ="src/main/resources/static/images/";

    public static String saveImage(MultipartFile file) {
        File directory = new File(IMG_PATH);
        noDirectoryCase(directory);

        if(file.isEmpty()){
            return "images/default_coffee.png";
        }
        try {
            String fileName = saveFile(file);
            return "images/" + fileName;
        } catch (IOException e){
            throw new CafeException(ErrorCode.IMG_SAVE_ERROR);
        }
    }

    private static void noDirectoryCase(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private static String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = IMG_PATH + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);
        return fileName;
    }
}

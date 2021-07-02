package de.aicard.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

@Service
public class FileUploadService
{

    public void uploadFile(MultipartFile file) throws IOException
    {
        String path = System.getProperty("user.dir");
        path = path + "\\cardFiles\\" + System.currentTimeMillis()+ "_" + file.getOriginalFilename();
        path = path.replace("\\", "\\\\");
        System.out.println(path);
        file.transferTo(new File(path));

    }
}

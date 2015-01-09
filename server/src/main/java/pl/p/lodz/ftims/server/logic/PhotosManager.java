package pl.p.lodz.ftims.server.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class PhotosManager implements IPhotosManager {

    @Override
    public void savePhoto(byte[] photo, String filename) throws IOException {
        File photoDir = new File(PHOTOS_DIR + filename + ".jpg");
        try (FileOutputStream stream = new FileOutputStream(photoDir)) {
            stream.write(photo);
        }
    }

    @Override
    public byte[] readPhoto(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }

}

package com.rhr.imageclassificationbackend.controllers.File;
import com.rhr.imageclassificationbackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload/file")
@CrossOrigin
public class FileController {
    public static final String MODEL_PYTHON_SERVICE_ENDPOINT = "http://localhost:5000/predict";
    public static final String IMAGE_PATH = "C:\\Users\\user\\IdeaProjects\\imageclassificationbackend\\images\\";
    private final RestTemplate restTemplate;

    @Autowired
    public FileController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public ResponseEntity uploadImageViaPath(@RequestParam("imageFile")MultipartFile file,
                                             @RequestParam("imageName") String name){
        try {
            Path trgtPath = Paths.get(formulatePath(name));
            file.transferTo(trgtPath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(getModifiedPath(formulatePath(name)),headers);
            Message message = restTemplate.postForObject(MODEL_PYTHON_SERVICE_ENDPOINT, entity, Message.class);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private String formulatePath(@RequestParam("imageName") String name) {
        return IMAGE_PATH + name + ".jpg";
    }

    private String getModifiedPath(String path) {
        return path.replaceAll("\\\\", "\\\\\\\\");
    }

    private FileApiResponse buildResponse(String path, String output) {
        return new FileApiResponse().builder()
                .path(path)
                .output(output)
                .build();
    }
}

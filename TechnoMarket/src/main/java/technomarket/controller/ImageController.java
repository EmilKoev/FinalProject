package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.pojo.ProductImage;
import technomarket.model.pojo.User;
import technomarket.service.ProductImageService;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ImageController extends Controller{

    @Autowired
    ProductImageService productImageService;

    @GetMapping(value = "/images/{id}", produces = "image/*")
    public byte[] getImageById(@PathVariable int id) throws IOException {
        ProductImage image = productImageService.getById(id);
        String url = image.getUrl();
        File file = new File(url);
        return Files.readAllBytes(file.toPath());
    }

    @GetMapping(value = "/download/{id}", produces="image/*")
    public ResponseEntity download(@PathVariable int id) {
        return productImageService.download(id);
    }

    @PostMapping("/images/multi-upload/{productId}")
    public List<ProductImage> multiUpload(@RequestParam("files") MultipartFile[] files,
                                      @PathVariable int productId,
                                          HttpSession session) {
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return productImageService.multiUpload(files, productId);
    }

    @PostMapping("/images/upload/{productId}")
    public ProductImage upload(@RequestParam("file") MultipartFile file,
                                                  @PathVariable int productId,
                                                  HttpSession session) {
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        return productImageService.upload(file, productId);
    }

}

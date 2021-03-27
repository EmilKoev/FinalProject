package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import technomarket.model.pojo.ProductImage;
import technomarket.service.ProductImageService;
import technomarket.service.ProductService;

import java.io.*;
import java.nio.file.Files;

@RestController
public class ImagesController extends Controller {

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @Value("${file.path}")
    private String filePath;

    @PutMapping("/images/{id}/upload")
    public ProductImage upload(@PathVariable int id, @RequestPart MultipartFile file) throws IOException {
        File pFile = new File(filePath + File.separator + System.nanoTime() + ".png");
        OutputStream stream = new FileOutputStream(pFile);
        stream.write(file.getBytes());
        ProductImage productImage = new ProductImage();
        productImage.setUrl(pFile.getAbsolutePath());
        productImage.setProduct(productService.getById(id));
        productImageService.save(productImage);
        stream.close();
        return productImage;
    }

    @GetMapping(value = "/images/{id}", produces = "image/*")
    public byte[] download(@PathVariable int id) throws IOException {
        ProductImage image = productImageService.getById(id);
        String url = image.getUrl();
        File file = new File(url);
        return Files.readAllBytes(file.toPath());
    }
}

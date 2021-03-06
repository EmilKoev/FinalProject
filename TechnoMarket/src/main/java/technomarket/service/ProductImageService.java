package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import technomarket.exeptions.NotFoundException;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.ProductImage;
import technomarket.model.repository.ProductImageRepository;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    ProductService productService;

    @Autowired
    private ProductImageRepository repository;

    public ProductImage getById(int id) {
        Optional<ProductImage> product = repository.findById(id);
        if (!product.isPresent()){
            throw  new NotFoundException("Product image not found");
        }else {
            return product.get();
        }
    }

    public ProductImage upload(MultipartFile file, int productId) {
        Product product = productService.getById(productId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(filePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProductImage productImage = new ProductImage();
        productImage.setUrl(path.toString());
        productImage.setProduct(product);
        return repository.save(productImage);
    }

    @Transactional
    public List<ProductImage> multiUpload(MultipartFile[] files, int productId) {
        List<ProductImage> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(upload(file,productId)));
        return productService.getById(productId).getImages();
    }

    public ResponseEntity download(int id) {
        ProductImage productImage = getById(id);
        Path path = Paths.get(productImage.getUrl());
        Resource resource = null;
        try {
            resource =  new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

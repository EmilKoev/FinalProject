package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technomarket.exeptions.BadRequestException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.DiscountRequestDTO;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.Product;
import technomarket.model.repository.DiscountRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository repository;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmailService emailService;

    @Transactional
    public Discount addDiscount(DiscountRequestDTO discountDTO) {
        LocalDate start = discountDTO.getStartAt();
        LocalDate end = discountDTO.getEndAt();
        if (end.isBefore(start)){
            throw new BadRequestException("Start date must be before end date!");
        }
        if (discountDTO.getDiscountPercent() < 0){
            throw  new BadRequestException("Percentage of the discount must be more than 0!");
        }
        Discount discount = new Discount(discountDTO);
        repository.save(discount);
        for (Integer productId : discountDTO.getProductsId()) {
            Product product = productService.getById(productId);
            product.setDiscount(discount);
            discount.getProductList().add(product);
        }
        emailService.sendMessage(discount);
        return repository.save(discount);
    }

    public Discount getDiscount(int id) {
        Optional<Discount> discount = repository.findById(id);
        if (discount.isPresent()){
            return discount.get();
        }else {
            throw new NotFoundException("Discount not found!");
        }
    }

    public Discount edit(DiscountRequestDTO requestDiscount, int id) {
        Discount discount = getDiscount(id);
        LocalDate start = requestDiscount.getStartAt();
        LocalDate end = requestDiscount.getEndAt();
        if (end.isBefore(start)){
            throw new BadRequestException("Start date must be before end date!");
        }
        if (requestDiscount.getDiscountPercent() < 0){
            throw  new BadRequestException("Percentage of the discount must be more than 0!");
        }
        discount.setTitle(requestDiscount.getTitle());
        discount.setDiscountPercent(requestDiscount.getDiscountPercent());
        discount.setStartAt(requestDiscount.getStartAt());
        discount.setEndAt(requestDiscount.getEndAt());
        repository.save(discount);
        return  discount;
    }

    public void deleteDiscount(int id){
        Discount discount = getDiscount(id);
        repository.delete(discount);
    }

    public List<Discount> getAll() {
        return repository.findAll();
    }
}

package co.com.cidenet.hulkStore.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.cidenet.hulkStore.domains.Product;
import co.com.cidenet.hulkStore.dto.ProductDTO;
import co.com.cidenet.hulkStore.exceptions.ControlledException;
import co.com.cidenet.hulkStore.exceptions.ProductoInvalidoException;
import co.com.cidenet.hulkStore.exceptions.ProductoNoEncontradoException;
import co.com.cidenet.hulkStore.repositories.ProductRepository;
import co.com.cidenet.hulkStore.utils.Utilities;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionService transactionService;

    public List<ProductDTO> findAll() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = IterableUtils.toList(iterable);
        return Utilities.convertEntityListToDTO(productList);
    }

    public ProductDTO getProductDTOById(UUID productId) throws ControlledException {
        return getProductById(productId).getDTO();
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws ControlledException {
        ProductDTO productDTOSaved = saveProduct(productDTO);
        transactionService.createTransaction(productDTOSaved, true, productDTOSaved.getQuantity());
        return productDTOSaved;
    }

    public ProductDTO updateProduct(ProductDTO productDTO, UUID productId) throws ControlledException {
        Product product = getProductById(productId);
        ProductDTO productDTOSaved = saveProduct(productDTO);
        if (product.getQuantity() > productDTOSaved.getQuantity()) {
            transactionService.createTransaction(productDTOSaved, false, (product.getQuantity() - productDTOSaved.getQuantity()));
        } else if (product.getQuantity() < productDTOSaved.getQuantity()) {
            transactionService.createTransaction(productDTOSaved, true, (product.getQuantity() - productDTOSaved.getQuantity()));
        }
        return productDTOSaved;
    }
    
    private ProductDTO saveProduct(ProductDTO productDTO) throws ControlledException {
        Product product = new Product(productDTO);
        validateProduct(product);
        Product productSaved = productRepository.save(product);
        return productSaved.getDTO();
    }

    public void deleteProduct(UUID productId) throws ControlledException {
        ProductDTO productDTO = getProductDTOById(productId);
        transactionService.createTransaction(productDTO, false, productDTO.getQuantity());
        productRepository.deleteById(productId);
    }

    private void validateProduct(Product product) throws ControlledException {
        validateQuantity(product);
        validateValue(product);
    }

    private void validateValue(Product product) throws ControlledException {
        if (product.getValue() < 0) {
            throw new ProductoInvalidoException("El producto no tiene valor válido");
        }
    }

    private void validateQuantity(Product product) throws ControlledException {
        if (product.getQuantity() < 0) {
            throw new ProductoInvalidoException("El producto no tiene cantidad válido");
        }
    }

    private Product getProductById(UUID productId) throws ControlledException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado");
        }
    }

}

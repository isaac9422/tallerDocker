package co.com.cidenet.hulkStore.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.cidenet.hulkStore.domains.Product;
import co.com.cidenet.hulkStore.dto.ProductDTO;
import co.com.cidenet.hulkStore.exceptions.ControlledException;
import co.com.cidenet.hulkStore.exceptions.ProductoInvalidoException;
import co.com.cidenet.hulkStore.exceptions.ProductoNoEncontradoException;
import co.com.cidenet.hulkStore.repositories.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    TransactionService transactionService;

    @InjectMocks
    ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProductTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(1000.0);
        Product product = new Product(productDTO);

        Product productSaved = new Product();
        productSaved.setId(productDTO.getId());
        productSaved.setName("Product 2");
        productSaved.setQuantity(200l);
        productSaved.setValue(2000.0);

        Mockito.when(productRepository.save(product)).thenReturn(productSaved);
        Mockito.doNothing().when(transactionService).createTransaction(ArgumentMatchers.any(ProductDTO.class),
                ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyLong());

        ProductDTO productDTOResult = productService.createProduct(productDTO);
        Assert.assertNotNull(productDTOResult);
    }

    @Test
    public void deleteProductTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(1000.0);
        Product product = new Product(productDTO);

        Mockito.doNothing().when(productRepository).deleteById(product.getId());
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.deleteProduct(product.getId());

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(product.getId());
    }

    @Test
    public void updateProductTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(1000.0);
        Product product = new Product(productDTO);

        ProductDTO productDTOExpected = new ProductDTO();
        productDTOExpected.setId(productDTO.getId());
        productDTOExpected.setName("Product 2");
        productDTOExpected.setQuantity(200l);
        productDTOExpected.setValue(2000.0);
        Product productSaved = new Product(productDTOExpected);

        Mockito.when(productRepository.save(product)).thenReturn(productSaved);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Mockito.doNothing().when(transactionService).createTransaction(ArgumentMatchers.any(ProductDTO.class),
                ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyLong());

        ProductDTO productDTOResult = productService.updateProduct(productDTOExpected, product.getId());
        Assert.assertEquals(productDTOExpected, productDTOResult);
    }

    @Test
    public void retrieveProductTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(1000.0);
        Product product = new Product(productDTO);

        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ProductDTO productDTOExpected = productService.getProductDTOById(product.getId());
        Assert.assertEquals(productDTOExpected, productDTO);
    }

    @Test
    public void retrieveProductsTest() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(1000.0);
        Product product = new Product(productDTO);

        ProductDTO productDTOExpected = new ProductDTO();
        productDTOExpected.setId(UUID.randomUUID());
        productDTOExpected.setName("Product 2");
        productDTOExpected.setQuantity(200l);
        productDTOExpected.setValue(2000.0);
        Product productSaved = new Product(productDTOExpected);

        Product[] productList = { product, productSaved };

        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        productDTOList.add(productDTO);
        productDTOList.add(productDTOExpected);

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(productList));

        List<ProductDTO> productDTOListResult = productService.findAll();
        Assert.assertEquals(productDTOList, productDTOListResult);
    }

    @Test(expected = ProductoNoEncontradoException.class)
    public void retrieveProductNotRegisteredTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(1000.0);
        Product product = new Product(productDTO);

        productService.getProductDTOById(product.getId());
    }

    @Test(expected = ProductoInvalidoException.class)
    public void createProductQuantityInvalidTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(-100l);
        productDTO.setValue(1000.0);

        productService.createProduct(productDTO);
    }

    @Test(expected = ProductoInvalidoException.class)
    public void createProductValueInvalidTest() throws ControlledException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(UUID.randomUUID());
        productDTO.setName("Product");
        productDTO.setQuantity(100l);
        productDTO.setValue(-1000.0);

        productService.createProduct(productDTO);
    }

}

package co.com.cidenet.hulkStore.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cidenet.hulkStore.dto.ProductDTO;
import co.com.cidenet.hulkStore.exceptions.ControlledException;
import co.com.cidenet.hulkStore.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID productId) throws ControlledException {
        ResponseEntity<ProductDTO> responseObject;
        ProductDTO productDTO = productService.getProductDTOById(productId);
        responseObject = new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        return responseObject;
    }

    @PostMapping("")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) throws ControlledException {
        ResponseEntity<Void> responseObject;
        productService.createProduct(productDTO);
        responseObject = new ResponseEntity<Void>(HttpStatus.CREATED);
        return responseObject;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getProduct() throws ControlledException {
        ResponseEntity<List<ProductDTO>> responseObject;
        List<ProductDTO> productList = productService.findAll();
        responseObject = new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
        return responseObject;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable UUID productId)
            throws ControlledException {
        ResponseEntity<Void> responseObject;
        productService.updateProduct(productDTO, productId);
        responseObject = new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        return responseObject;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) throws ControlledException {
        ResponseEntity<Void> responseObject;
        productService.deleteProduct(productId);
        responseObject = new ResponseEntity<Void>(HttpStatus.OK);
        return responseObject;
    }
}

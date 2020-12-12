package co.com.cidenet.hulkStore.domains;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import co.com.cidenet.hulkStore.dto.TransactionDTO;

@Entity
@Table(name = "transaction")
public class Transaction extends BasicEntity<TransactionDTO> {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Double valueTransaction;

    @Column
    private Boolean type;

    @Column
    private Long quantity;

    public Transaction() {
        super();
    }

    public Transaction(TransactionDTO dto) {
        super(dto);
        Product product = new Product(dto.getProductDTO());
        this.setProduct(product);
    }

    @Override
    public TransactionDTO getDTO() {
        TransactionDTO transactionDTO = new TransactionDTO();
        BeanUtils.copyProperties(this, transactionDTO);
        transactionDTO.setProductDTO(this.product.getDTO());
        return transactionDTO;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getValueTransaction() {
        return valueTransaction;
    }

    public void setValueTransaction(Double valueTransaction) {
        this.valueTransaction = valueTransaction;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((valueTransaction == null) ? 0 : valueTransaction.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        } else if (!dateCreated.equals(other.dateCreated))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (valueTransaction == null) {
            if (other.valueTransaction != null)
                return false;
        } else if (!valueTransaction.equals(other.valueTransaction))
            return false;
        return true;
    }

}

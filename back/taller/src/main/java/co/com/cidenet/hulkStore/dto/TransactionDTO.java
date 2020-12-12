package co.com.cidenet.hulkStore.dto;

import java.util.Date;
import java.util.UUID;

public class TransactionDTO extends DTO {

    private UUID id;
    private Date dateCreated;
    private ProductDTO productDTO;
    private Double valueTransaction;
    private Boolean type;
    private Long quantity;

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

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
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
        result = prime * result + ((productDTO == null) ? 0 : productDTO.hashCode());
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
        TransactionDTO other = (TransactionDTO) obj;
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
        if (productDTO == null) {
            if (other.productDTO != null)
                return false;
        } else if (!productDTO.equals(other.productDTO))
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

package productservice.model;

public class AddProductResponse extends AbstractResponse {
	
	private Long productId;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}

package dw.trabalho03.model;

import java.util.List;

public class IdProduct {
	private List<Long> id;

	public IdProduct() {

	}

	public IdProduct(List<Long> id) {
		this.id = id;
	}

	public List<Long> getId() {
		return id;
		}
	
		public void setId(List<Long> id) {
		this.id = id;
		}
}
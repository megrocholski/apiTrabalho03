package dw.trabalho03.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dw.trabalho03.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value = "SELECT * FROM cart WHERE id_user =?1", nativeQuery = true)
	List<Cart> findCartsByUser(long idUser);

	List<Cart> findByActive(boolean active);

	@Query(value = "SELECT * FROM cart WHERE active=true and id_user=?1", nativeQuery = true)
	List<Cart> findByActiveAndUser(long idUser);

	@Query(value = "DELETE FROM item_cart WHERE id_cart=?1 and id_product=?2", nativeQuery = true)
	void deleteCartItem(long id_cart, long id_product);

}

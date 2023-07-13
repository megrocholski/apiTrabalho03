package dw.trabalho03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dw.trabalho03.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	// @Query(value = "SELECT * FROM product WHERE SUBSTRING(title, 0) = '?1'",
	// nativeQuery = true)
	@Query(value = "SELECT * FROM product WHERE title LIKE ?1", nativeQuery = true)
	List<Product> findByTitle(String title);

	// @Query(value = "SELECT * FROM product WHERE category =?1", nativeQuery =
	// true)
	List<Product> findByCategory(String category);

	@Query(value = "SELECT * FROM product WHERE category =?1 and title LIKE '%?2%'", nativeQuery = true)
	List<Product> findByCategoryAndTitle(String category, String title);

}

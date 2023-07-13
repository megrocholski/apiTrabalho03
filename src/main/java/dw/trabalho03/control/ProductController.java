package dw.trabalho03.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho03.model.Product;
import dw.trabalho03.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductRepository rep;

	// rota para buscar todos os jogadores
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String category) {
		try {
			List<Product> lj = new ArrayList<Product>();

			if (nome == null && category == null) {
				rep.findAll().forEach(lj::add);
			} else if (nome != null && category == null) {
				rep.findByTitle("%" + nome + "%").forEach(lj::add);
			} else if (nome == null && category != null) {
				rep.findByCategory(category).forEach(lj::add);
			}

			if (lj.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para criar um jogador
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product jo) {
		try {
			if (jo.getCategory() != "" && jo.getDescription() != "" && jo.getImage() != "" && jo.getPrice() != 0
					&& jo.getTitle() != "") {
				Product j = rep
						.save(new Product(jo.getTitle(), jo.getPrice(), jo.getCategory(), jo.getDescription(),
								jo.getImage()));
				return new ResponseEntity<>(j, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para buscar um jogador pelo id
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		Optional<Product> data = rep.findById(id);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// rota para atualizar os dados de um jogador
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product jo) {

		Optional<Product> data = rep.findById(id);

		if (data.isPresent()) {
			Product j = data.get();
			if (jo.getCategory() != "") {
				j.setCategory(jo.getCategory());
			}
			if (jo.getDescription() != "") {
				j.setDescription(jo.getDescription());
			}
			if (jo.getImage() != "") {
				j.setImage(jo.getImage());
			}
			if (jo.getPrice() != 0) {
				j.setPrice(jo.getPrice());
			}
			if (jo.getTitle() != "") {
				j.setTitle(jo.getTitle());
			}

			return new ResponseEntity<>(rep.save(j), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// rota para deletar um jogador de acordo com seu id
	@DeleteMapping("/product/{id}")
	public ResponseEntity<HttpStatus> deleteProdut(@PathVariable("id") long id) {
		try {
			rep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para deletar todos os jogadores
	@DeleteMapping("/product")
	public ResponseEntity<HttpStatus> deleteAllProducts() {
		try {
			rep.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package dw.trabalho03.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import dw.trabalho03.model.Cart;
import dw.trabalho03.model.IdProduct;
import dw.trabalho03.model.Product;
import dw.trabalho03.model.User;
import dw.trabalho03.repository.CartRepository;
import dw.trabalho03.repository.ProductRepository;
import dw.trabalho03.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class CartController {

	@Autowired
	CartRepository rep;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;

	// rota para buscar todos os pagamentos
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> getAllCarts() {
		try {
			List<Cart> lj = new ArrayList<Cart>();

			// if (jogador == null) {
			rep.findAll().forEach(lj::add);
			// } else {
			// Optional<Jogador> jg = jRepository.findById(jogador);
			// if (jg.isPresent()) {
			// Jogador jog = new Jogador(jg.get().getNome(), jg.get().getEmail(),
			// jg.get().getDataNasc());
			// rep.findPagamentosByJogador(jog).forEach(lj::add);
			// }
			// }

			if (lj.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para buscar pagamentos de um determinado jogador
	@GetMapping("/cart/user/{id}")
	public ResponseEntity<List<Cart>> getCartsByUser(@PathVariable("id") long id) {
		try {
			List<Cart> lj = new ArrayList<Cart>();

			Optional<User> jg = userRepository.findById(id);
			if (jg.isPresent()) {
				User jog = new User(jg.get().getName(), jg.get().getEmail(), jg.get().getPhone(),
						jg.get().getUsername(), jg.get().getPassword());
				rep.findCartsByUser(id).forEach(lj::add);
			}

			if (lj.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para buscar pagamentos de um determinado jogador
	@GetMapping("/cart/user/active/{id}")
	public ResponseEntity<List<Cart>> getCartsByActiveAndUser(@PathVariable("id") long id) {
		try {
			List<Cart> lj = new ArrayList<Cart>();

			Optional<User> jg = userRepository.findById(id);
			if (jg.isPresent()) {
				// User jog = new User(jg.get().getName(), jg.get().getEmail(),
				// jg.get().getPhone(),
				// jg.get().getUsername(), jg.get().getPassword());
				rep.findByActiveAndUser(id).forEach(lj::add);
				// List<Cart> data = rep.findByActiveAndUser(id);
				return new ResponseEntity<>(lj, HttpStatus.OK);
				// if (data.isPresent()) {
				// Cart cart = data.get();
				// return new ResponseEntity<>(cart, HttpStatus.OK);
				// } else {
				// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				// }
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para criar um novo pagamento
	@PostMapping("/cart/{id}")
	public ResponseEntity<Cart> createCart(@PathVariable("id") long id, @RequestBody IdProduct pg) {
		try {
			// JogadorController jogController = new JogadorController();
			// ResponseEntity<Jogador> j = jogController.getJogadorById(id);
			// System.out.println(pg.getDate().toString());
			// Date newDate = simpleDateFormat.parse(Sdate);
			Date newDate = new Date();
			Optional<User> data = userRepository.findById(id);
			if (data.isPresent()) {
				User jg = data.get();
				if (pg != null) {
					List<Product> list_aux = new ArrayList<Product>();

					for (int i = 0; i < pg.getId().size(); i++) {
						Optional<Product> product = productRepository.findById(pg.getId().get(i));
						list_aux.add(i, product.get());
					}
					Cart p = rep.save(new Cart(newDate, jg, list_aux));

					return new ResponseEntity<>(p, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para buscar pagamento de acordo com o id
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("id") long id) {
		Optional<Cart> data = rep.findById(id);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// rota para atualizar dados de um pagamento
	@PutMapping("/cart/{id}/{idUser}")
	public ResponseEntity<Cart> updateCart(@PathVariable("id") long id, @RequestBody IdProduct pg,
			@PathVariable("idUser") long idUser) {

		Optional<Cart> data = rep.findById(id);

		if (data.isPresent()) {
			Optional<User> data2 = userRepository.findById(idUser);

			if (data2.isPresent()) {
				Cart p = data.get();
				List<Product> list_aux = new ArrayList<Product>();

				if (pg != null) {
					for (int i = 0; i < pg.getId().size(); i++) {
						Optional<Product> product = productRepository.findById(pg.getId().get(i));
						list_aux.add(i, product.get());
					}
					p.setProducts(list_aux);
				}

				return new ResponseEntity<>(rep.save(p), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/cart/close/{id}/{idUser}")
	public ResponseEntity<Cart> closeCart(@PathVariable("id") long id,
			@PathVariable("idUser") long idUser) {

		Optional<Cart> data = rep.findById(id);

		if (data.isPresent()) {
			Optional<User> data2 = userRepository.findById(idUser);

			if (data2.isPresent()) {
				Cart p = data.get();
				p.setActive(false);

				return new ResponseEntity<>(rep.save(p), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// rota para deletar um pagamento de acordo com o id
	@DeleteMapping("cart_item/{id_cart}/{id_product}")
	public ResponseEntity<HttpStatus> deleteCartItem(@PathVariable("id_cart") long id_cart,
			@PathVariable("id_product") long id_product) {
		try {
			rep.deleteCartItem(id_cart, id_product);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("cart/{id}")
	public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") long id) {
		try {
			rep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para deletar todos os pagamentos
	@DeleteMapping("/cart")
	public ResponseEntity<HttpStatus> deleteAllCart() {
		try {
			rep.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

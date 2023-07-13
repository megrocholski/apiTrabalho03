package dw.trabalho03.control;

import java.io.Console;
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

import dw.trabalho03.model.User;
import dw.trabalho03.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository rep;

	// rota para buscar todos os jogadores
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
		try {
			List<User> lj = new ArrayList<User>();

			if (name == null) {
				rep.findAll().forEach(lj::add);
			} else {
				rep.findByName(name).forEach(lj::add);
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
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User jo) {
		try {
			if (jo.getName() != "" && jo.getEmail() != "" && jo.getPhone() != "" && jo.getUsername() != ""
					&& jo.getPassword() != "") {
				User j = rep
						.save(new User(jo.getEmail(), jo.getUsername(), jo.getPassword(), jo.getName(), jo.getPhone()));
				return new ResponseEntity<>(j, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para buscar um jogador pelo id
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> data = rep.findById(id);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/login")
	public ResponseEntity<User> login(@RequestParam(required = true) String username, @RequestParam(required = true) String password) {
		
		Optional<User> data = rep.login(username, password);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// rota para atualizar os dados de um jogador
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User jo) {

		Optional<User> data = rep.findById(id);

		if (data.isPresent()) {
			User j = data.get();
			if (jo.getName() != "") {
				j.setName(jo.getName());
			}
			if (jo.getEmail() != "") {
				j.setEmail(jo.getEmail());
			}
			if (jo.getPassword() != "" && jo.getPassword() != null) {
				j.setPassword(jo.getPassword());
			}
			if (jo.getPhone() != "") {
				j.setPhone(jo.getPhone());
			}
			if (jo.getUsername() != "") {
				j.setUsername(jo.getUsername());
			}

			return new ResponseEntity<>(rep.save(j), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// rota para deletar um jogador de acordo com seu id
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			rep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rota para deletar todos os jogadores
	@DeleteMapping("/user")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			rep.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

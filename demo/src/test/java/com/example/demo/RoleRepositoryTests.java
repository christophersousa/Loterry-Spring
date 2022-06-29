// package com.example.demo;

// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import
// org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.annotation.Rollback;

// import com.example.demo.model.Authority;
// import com.example.demo.repository.AuthoryRepository;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = Replace.NONE)
// @Rollback(false)
// public class RoleRepositoryTests {

// @Autowired
// private AuthoryRepository repo;

// @Test
// public void testCreateRoles() {
// Authority user = new Authority();
// Authority admin = new Authority();
// user.setAuthority("USER");
// admin.setAuthority("ADMIN");

// repo.saveAll(List.of(user, admin));

// List<Authority> listRoles = repo.findAll();

// assertThat(listRoles.size()).isEqualTo(3);
// }

// }

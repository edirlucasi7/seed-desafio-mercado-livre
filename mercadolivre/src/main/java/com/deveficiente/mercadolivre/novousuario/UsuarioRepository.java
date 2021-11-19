package com.deveficiente.mercadolivre.novousuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Usuario findByEmail(String email);
}

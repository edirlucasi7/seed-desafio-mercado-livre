package com.deveficiente.mercadolivre.novoproduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.deveficiente.mercadolivre.novacategoria.Categoria;
import com.deveficiente.mercadolivre.novaopiniaoproduto.Opiniao;
import com.deveficiente.mercadolivre.novapergunta.Pergunta;
import com.deveficiente.mercadolivre.novousuario.Usuario;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@Positive
	private @NotNull BigDecimal valor;
	private @NotNull int quantidade;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private @NotNull @Size(min = 3) Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	private @NotBlank @Size(max = 1000) String descricao;
	@ManyToOne
	private @NotNull Categoria categoria;
	private @NotNull LocalDateTime instanteCriacao;
	@OneToOne
	private @NotNull Usuario dono;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@Deprecated
	public Produto() { }
	
	public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor, @NotNull int quantidade,
			@Valid @NotNull @Size(min = 3) Collection<NovaCaracteristicaRequest> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.instanteCriacao = LocalDateTime.now().withNano(0);
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
	
		Assert.isTrue(this.caracteristicas.size()>=3,"é preciso ter no minimo tres caracteristicas para um produto!");
	}

	@Deprecated
	public void setDono(Usuario usuario) {
		this.dono = usuario;	
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", caracteristicas=" + caracteristicas + ", descricao=" + descricao + ", categoria=" + categoria
				+ ", instanteCriacao=" + instanteCriacao + ", dono=" + dono + ", links=" + imagens + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

	public void associaImagens(@NotNull Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this,link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void abateQuantidadeEstoque(@Positive @NotNull int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade não pode chegar aqui menor ou igual a zero!");
		this.quantidade -= quantidade; 
	}

	public Usuario getDono() {
		return dono;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getInstanteCriacao() {
		return instanteCriacao;
	}

	public Set<ImagemProduto> getLinks() {
		return imagens;
	}

	public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	public <T extends Comparable<T>> SortedSet<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
	}
	
	public <T> Set<T> mapOpinioes(Function<Opiniao, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
}

package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import br.com.alura.loja.model.Produto;

public class ProdutoDao {

	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
	    this.em.merge(produto);
	}
	
	   
    public void delete(Produto produto) {
        produto = em.merge(produto);
        this.em.remove(produto);
     }
    
    public Produto buscarProdutoPorId(Long id) {
        return em.find(Produto.class, id);
    }
    
    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p"; // Eu preciso passar Entidade (Produto) e não o nome da tabela.
        return em.createQuery(jpql, Produto.class).getResultList(); 
      //o createQuery apenas monta o select, mas quem busca a informação é o getResultList();
    }
    
    public List<Produto> buscarPorNome(String nome) { // no parâmetro eu passo qual é o nome. 
        String jpql = "SELECT p FROM Produto WHERE p.nome = :nome"; // Eu preciso passar o nome do atributo (nome no caso) que eu quero buscar.
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome) // digo qual é o apelido do parâmetro dinâmico // em seguida qual é o nome do parâmetro.
                .getResultList(); 
      //o createQuery apenas monta o select, mas quem busca a informação é o getResultList();
    }
    
    public List<Produto> buscarPorNomeDaCategoria(String nome) { // no parâmetro eu passo qual é o nome. 
        String jpql = "SELECT p FROM Produto WHERE p.categoria.nome = :nome"; // Eu preciso passar o nome do atributo (categoria no caso) que eu quero buscar, em seguida qual é o atributo que eu quero dentro de categoria.
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome) // digo qual é o apelido do parâmetro dinâmico // em seguida qual é o nome do parâmetro.
                .getResultList(); 
      //o createQuery apenas monta o select, mas quem busca a informação é o getResultList();
    }
    
    public BigDecimal buscarPrecoDoProdutoPorNome(String nome) { // no parâmetro eu passo qual é o nome. 
        String jpql = "SELECT p.preco FROM Produto WHERE p.nome = :nome"; // Eu preciso passar o nome do atributo (p.preco) que eu quero buscar.
        return em.createQuery(jpql, BigDecimal.class) //Quero apenas o preço, portanto passo apenas o seu tipo (BigDecimal)
                .setParameter("nome", nome) // digo qual é o apelido do parâmetro dinâmico // em seguida qual é o nome do parâmetro.
                .getSingleResult(); 
      //o createQuery apenas monta o select, mas quem busca a informação é o getSingleResult();
    }
}

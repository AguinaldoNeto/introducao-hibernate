package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		
		cadastrarProduto();
		EntityManager em =JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarProdutoPorId(1l);
		System.out.println(p.getPreco());
	}

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria();
		celulares.setNome("CELULARES");
		
		Produto celular = new Produto();
		celular.setNome("Apple X");
		celular.setDescricao("Top da Galáxia");
		celular.setPreco(new BigDecimal("10000"));
		celulares.setNome("CELULARES");

		celular.setCategoria(celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin(); // JPA e entity manager, pega lá a transação e inicia ela.
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit(); // JPA e entity manager, já fez a operação, commita ela no bd.
		em.close(); //Já fez tudo, então pode finalizar.
    }
}

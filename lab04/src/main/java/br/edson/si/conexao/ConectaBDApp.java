package br.edson.si.conexao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.hibernate.annotations.Where;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

import br.edson.si.Model.Carro;
import br.edson.si.Model.Cliente;
import br.edson.si.Model.Locacao;
import br.edson.si.Model.LocacaoCarrosMaisAlugados;

public class ConectaBDApp {

	private static final int Long = 0;

	public static void inserir(EntityManager em) throws ParseException {

//		Cliente cliente1 = new Cliente("11111111111", "Silvio Santos");
		Cliente cliente2 = new Cliente("22211111111", "Fausto Silva");
//		Cliente cliente3 = new Cliente("33311111111", "Raul Gil");
//		Cliente cliente4 = new Cliente("33333333333", "Carlos Silvio");

		Carro carro1 = new Carro("Fusca", "PXP4505");
//		Carro carro2 = new Carro("Camaro", "CXF8005");
//		Carro carro3 = new Carro("Opala", "PPX4185");

//		Locacao locacao1 = new Locacao(cliente3, carro3, new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2020"), null);
//		Locacao locacao2 = new Locacao(cliente1, carro2, new SimpleDateFormat("dd/MM/yyyy").parse("20/01/2020"),
//				new SimpleDateFormat("dd/MM/yyyy").parse("27/01/2020"));
//		Locacao locacao3 = new Locacao(cliente2, carro1, new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2020"), null);
//		Locacao locacao4 = new Locacao(cliente4, carro1, new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2020"),
//				new SimpleDateFormat("dd/MM/yyyy").parse("15/03/2020"));
		Locacao locacao5 = new Locacao(cliente2, carro1, new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2020"),
		new SimpleDateFormat("dd/MM/yyyy").parse("15/03/2020"));

//		em.persist(cliente1);
//		em.persist(cliente2);
//		em.persist(cliente3);
//		em.persist(cliente4);
//
//		em.persist(carro1);
//		em.persist(carro2);
//		em.persist(carro3);
//
//		em.persist(locacao1);
//		em.persist(locacao2);
//		em.persist(locacao3);
//		em.persist(locacao4);
		em.persist(locacao5);

	}

	public static void listarOrdenadoPlaca(EntityManager em) {
		CriteriaBuilder b = em.getCriteriaBuilder();
		CriteriaQuery<Carro> cQuery = b.createQuery(Carro.class);
		Root<Carro> carro = cQuery.from(Carro.class);
		Order ordem = b.desc(carro.<String>get("placaCarro"));

		cQuery.select(carro);
		cQuery.orderBy(ordem);

		TypedQuery<Carro> query = em.createQuery(cQuery);
		List<Carro> carros = query.getResultList();
		System.out.println("listando carros");
		for (Carro c : carros) {
			System.out.println(c.getModeloCarro());
		}
	}

	public static void buscandoUsaurioNome(EntityManager em) {
		TypedQuery<Cliente> clientes = em.createQuery("from Cliente c  where c.nomeCliente like 'Silvio%'",
				Cliente.class);

		for (Cliente c : clientes.getResultList()) {
			System.out.println("cliente com nome Silvio: " + c.getNomeCliente());
		}

	}

	public static void buscarLocacaoCliente(String cpf, EntityManager em) {

		Cliente c = em.createQuery("from Cliente c where c.cpfCliente = :cpf", Cliente.class).setParameter("cpf", cpf)
				.getSingleResult();
		Long a = c.getIdCliente();
		Locacao l = em.createQuery("from Locacao l where l.cliente.idCliente = :id", Locacao.class)
				.setParameter("id", a).getSingleResult();
		System.out.println("Mostrando carro \n Carro: " + l.getCarro().getModeloCarro() + " placa: "
				+ l.getCarro().getPlacaCarro());

	}

	public static void buscarCarrosAlugadosNaoDevolvidos(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery("from Locacao l where l.dataDevolucao = null", Locacao.class);

		for (Locacao l : locacoes.getResultList()) {
			System.out.println("Carro não devolvido: " + l.getCarro().getModeloCarro());
		}
	}

	public static void carrosAlugadosUmaVez(EntityManager em) {
		TypedQuery<LocacaoCarrosMaisAlugados> query = em
				.createQuery(
						"select new br.edson.si.Model.LocacaoCarrosMaisAlugados(l.carro.idCarro, "
								+ "count(l.carro.idCarro) ) " + "from Locacao l group by l.carro.idCarro",
						LocacaoCarrosMaisAlugados.class);
		ArrayList<String> alugados = new ArrayList();
		for (LocacaoCarrosMaisAlugados l : query.getResultList()) {
			Carro car = em.createQuery("from Carro c where idCarro = :id", Carro.class)
					.setParameter("id", l.getIdCarro()).getSingleResult();
			alugados.add("Este " + car.getModeloCarro() + " foi alugado " + l.getVezesAlugado());
		}

		for (String c : alugados) {
			System.out.printf(c + "\n");

		}

	}

	public static void buscandoQuantidadeClientes(EntityManager em) {
		TypedQuery<Long> query = em.createQuery("select count(c)from Cliente c", Long.class);

		Long qtdClientes = query.getSingleResult();
		System.out.println("Quantidade de Clientes: " + qtdClientes);
	}

	public static void carroMaisAlugado(EntityManager em) {
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
//			 et.begin();
//			 inserir(em);
//			 et.commit();
//
//			listarOrdenadoPlaca(em);
//			System.out.println("***************************************************");
//			 
//			buscandoUsaurioNome(em);
//			System.out.println("***************************************************");
//			 
//			buscarLocacaoCliente("11111111111", em);
//			 
//			System.out.println("***************************************************");
//			buscarCarrosAlugadosNaoDevolvidos(em);
//			System.out.println("***************************************************");
//			 
//			carrosAlugadosUmaVez(em);
//			System.out.println("***************************************************");
//			 
//			buscandoQuantidadeClientes(em);
//			System.out.println("***************************************************");
//
			carroMaisAlugado(em);
//			System.out.println("***************************************************");
			
			//JOptionPane.showMessageDialog(null, "sucesso");
		} catch (Exception e) {
			et.rollback();
			System.out.println("Erro na operação. " + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}

	}

}

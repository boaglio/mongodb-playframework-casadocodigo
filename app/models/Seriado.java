package models;

import java.util.List;

import javax.persistence.Id;

import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.db.ebean.Model;
import play.modules.mongodb.jackson.MongoDB;

public class Seriado extends Model {

	private static final long serialVersionUID = 6338123117193466015L;

	@Id
	@ObjectId
	public String id;

	public String nome;

	public List<String> personagens;

	public static List<Seriado> all() {
		return Seriado.collection.find().toArray();
	}

	public static Seriado findById(String id) {
		System.out.println("Busca por id = " + id);
		Seriado seriado = Seriado.collection.findOneById(id);
		System.out.println("seriado= " + seriado);
		return seriado;
	}

	public static void insert(Seriado seriado) {
		System.out.println("Adicionando seriado:" + seriado);
		Seriado.collection.insert(seriado);
	}

	public static void update(Seriado seriado) {
		System.out.println("Alterando seriado:" + seriado);
		Seriado.collection.save(seriado);
	}

	public static void remove(String id) {
		System.out.println("Removendo id = " + id);
		Seriado.collection.removeById(id);
	}

	private static JacksonDBCollection<Seriado,String> collection = MongoDB.getCollection("seriados",Seriado.class,String.class);

	@Override
	public String toString() {
		return "Seriado [id=" + id + ", nome=" + nome + ", personagens=" + personagens + "]";
	}

}

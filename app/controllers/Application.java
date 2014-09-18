package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Seriado;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	private static final Form<Seriado> seriadoForm = Form.form(Seriado.class);

	/**
	 * GET /
	 */
	public static Result index() {
		return ok(views.html.index.render(Seriado.all()));
	}

	/**
	 * GET /novo
	 */
	public static Result novo() {

		return ok(views.html.novo.render(seriadoForm));

	}

	/**
	 * GET /:id
	 */
	public static Result detalhar(String id) {

		Form<Seriado> seriadoForm = form(Seriado.class).fill(Seriado.findById(id));

		System.out.println("seriado para alterar=" + seriadoForm.get());

		Seriado seriadoArmazenado = Seriado.findById(id);

		int totalPersonagens = 0;
		if (seriadoArmazenado.personagens != null) {
			totalPersonagens = seriadoArmazenado.personagens.size();
		}

		return ok(views.html.alterar.render(id,seriadoForm,seriadoArmazenado,totalPersonagens));
	}

	/**
	 * POST /:id
	 */
	public static Result alterar(String id) {

		Seriado seriadoArmazenado = Seriado.findById(id);

		int totalPersonagens = 0;
		if (seriadoArmazenado.personagens != null) {
			totalPersonagens = seriadoArmazenado.personagens.size();
		}

		System.out.println("alterarForm antes=" + form(Seriado.class).fill(Seriado.findById(id)));

		Form<Seriado> alterarForm = form(Seriado.class).bindFromRequest();
		if (alterarForm.hasErrors()) { return badRequest(views.html.alterar.render(id,alterarForm,seriadoArmazenado,totalPersonagens)); }

		HashMap<String,String> parametros = (HashMap<String,String>) alterarForm.data();

		Seriado seriadoAlterado = new Seriado();
		seriadoAlterado.id = parametros.get("id");
		seriadoAlterado.nome = parametros.get("nome");
		List<String> personagens = new ArrayList<String>();
		personagens.add(parametros.get("personagem1"));
		personagens.add(parametros.get("personagem2"));
		personagens.add(parametros.get("personagem3"));
		personagens.add(parametros.get("personagem4"));
		personagens.add(parametros.get("personagem5"));
		personagens.add(parametros.get("personagem6"));
		seriadoAlterado.personagens = personagens;

		System.out.println("seriadoAlterado=" + seriadoAlterado);

		Seriado.update(seriadoAlterado);

		return redirect(routes.Application.index());

	}

	/**
	 * POST /
	 */
	public static Result gravar() {

		Form<Seriado> form = seriadoForm.bindFromRequest();
		HashMap<String,String> parametros = (HashMap<String,String>) form.data();

		Seriado seriadoNovo = new Seriado();
		seriadoNovo.nome = parametros.get("nome");

		System.out.println("seriado novo = " + seriadoNovo);

		List<String> personagens = new ArrayList<String>();
		personagens.add(parametros.get("personagem1"));
		personagens.add(parametros.get("personagem2"));
		personagens.add(parametros.get("personagem3"));
		personagens.add(parametros.get("personagem4"));
		personagens.add(parametros.get("personagem5"));
		personagens.add(parametros.get("personagem6"));
		seriadoNovo.personagens = personagens;

		Seriado.insert(seriadoNovo);

		return redirect(routes.Application.index());

	}

	/**
	 * POST /:id/remover
	 */
	public static Result remover(String id) {
		Seriado.remove(id);
		return index();
	}

}

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.OracleConnection;
import dao.PrenotazioneDao;
import dao.PrenotazioneDaoImpl;
import dao.ViaggioDao;
import dao.ViaggioDaoImpl;
import domain_control.Prenotazione;

/**
 * Servlet implementation class Prenotazioni
 */
@WebServlet("/prenotazioni")
public class Prenotazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Prenotazioni() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// connessione
		try (DaoFactory daoFactory = DaoFactory.getNewInstance()) {
			PrenotazioneDao pd = daoFactory.getPrenotazioneDao();
			List<Prenotazione> result = pd.leggiTutto();
			if (result == null) {
				result = new ArrayList<>();
				Prenotazione prenotazione = new Prenotazione();
				prenotazione.setCodPren(20);
				prenotazione.setnGiorni(15);
				result.add(prenotazione);
				prenotazione = new Prenotazione();
				prenotazione.setCodPren(21);
				prenotazione.setnGiorni(7);
				result.add(prenotazione);
			}
			request.setAttribute("prenotazioni", result);
			ServletContext servletContext = this.getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/WEB-INF/jsp/prenotazioni.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.OracleConnection;
import dao.ViaggioDao;
import dao.ViaggioDaoImpl;
import domain_control.Viaggio;

@WebServlet("/Viaggi")
public class Viaggi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Viaggi() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie = new Cookie("lastInteraction", "ftyfggfdvzzvxv");
		response.addCookie(cookie);
		String option = request.getParameter("option");
		if ("cerca".equals(option)) {
			String sId = request.getParameter("id");
			try {
				long id = Long.parseLong(sId);
				try (DaoFactory daoFactory = DaoFactory.getNewInstance()) {
					Viaggio viaggio = daoFactory.getViaggioDao().leggi(id);
					if (viaggio == null) {
						log("Viaggio con id " + sId + " non trovato");
						request.setAttribute("error", "Viaggio non trovato");
					} else {
						request.setAttribute("viaggio", viaggio);
					}
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/cerca.jsp").forward(request,
							response);
				}
			} catch (NumberFormatException nfe) {
				log("id non valido", nfe);
				request.setAttribute("error", "Serve un numero valido");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/cerca.jsp").forward(request,
						response);
			}
		} else if ("modifica".equals(option)) {
			String sId = request.getParameter("id");
			try {
				long id = Long.parseLong(sId);
				try (DaoFactory daoFactory = DaoFactory.getNewInstance()) {
					Viaggio viaggio = daoFactory.getViaggioDao().leggi(id);
					if (viaggio == null) {
						log("Viaggio con id " + sId + " non trovato");
						request.setAttribute("error", "Viaggio non trovato");
						this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/cerca.jsp").forward(request,
								response);
					} else {
						request.setAttribute("viaggio", viaggio);
						this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/form.jsp").forward(request,
								response);
					}
				}
			} catch (NumberFormatException nfe) {
				log("id non valido", nfe);
				request.setAttribute("error", "Serve un numero valido");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/cerca.jsp").forward(request,
						response);
			}
		} else if ("nuovo".equals(option)) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/form.jsp").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/cerca.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("option");

		// connessione
		OracleConnection oc = new OracleConnection();
		Connection c = oc.open();

		ViaggioDaoImpl vd = new ViaggioDaoImpl(c);

		if (option.equals("cerca")) {
			// chiamo DAO VIaggi, metodo cerca
			response.setContentType("text/html");

			long id = Long.parseLong(request.getParameter("id"));

			Viaggio v = vd.leggi(id);

			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head><title>HelloWorldServlet</title></head>");
			out.println("<body>");
			out.println("<p> Risultato ricerca viaggio " + request.getParameter("id") + "!</p>");
			out.println("<p> " + v.getLocalita() + "</p>");
			out.println("</body></html>");
		} else if (option.equals("inserisci")) {
			// chiamo DAO VIaggi, metodo cerca
			response.setContentType("text/html");

			String localita = request.getParameter("localita");
			long id = Long.parseLong(request.getParameter("id"));
			Viaggio v = new Viaggio();
			v.setCodViaggio(id);
			vd.inserisci(v);

			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head><title>HelloWorldServlet</title></head>");
			out.println("<body>");
			out.println("<p> Risultato ricerca viaggio " + request.getParameter("id") + "!</p>");
			out.println("<p> " + v.getLocalita() + "</p>");
			out.println("</body></html>");

		} else if ("save".equals(option)) {
			if (validateViaggio(request)) {
				try (DaoFactory daoFactory = DaoFactory.getNewInstance()) {
					ViaggioDao viaggioDao = daoFactory.getViaggioDao();
					Viaggio viaggio = new Viaggio();
					String sId = request.getParameter("id");
					viaggio.setLocalita(request.getParameter("localita"));
					viaggio.setStruttura(request.getParameter("struttura"));
					viaggio.setCosto(Double.parseDouble(request.getParameter("costo")));
					if (sId == null || "".equals(sId)) {
						long id = viaggioDao.inserisci(viaggio);
						log("Inserito viaggio con id " + id);
						HttpSession session = request.getSession();
						session.setAttribute("message", "Nuovo viaggio inserito.");
						response.sendRedirect(request.getContextPath() + "/Viaggi?option=cerca&id=" + id);
					} else {
						viaggio.setCodViaggio(Long.parseLong(sId));
						viaggioDao.aggiorna(viaggio.getCodViaggio(), viaggio);
						log("Aggiornato viaggio con id " + sId);
						HttpSession session = request.getSession();
						session.setAttribute("message", "Aggiornato viaggio.");
						response.sendRedirect(request.getContextPath() + "/Viaggi?option=cerca&id=" + sId);
					}
				}
			} else {
				request.setAttribute("error", "Dati non validi");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/viaggi/form.jsp").forward(request,
						response);

			}
		}

		oc.close();
	}

	private boolean validateViaggio(HttpServletRequest request) {
		return true;
//
	}

}

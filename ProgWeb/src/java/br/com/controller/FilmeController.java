package br.com.controller;

import br.com.Dao.UserFilmsDao;
import br.com.business.CommentBO;
import br.com.business.FilmesBO;
import br.com.business.UserBO;
import br.com.model.Comment;
import br.com.model.Filme;
import br.com.model.Permission;
import br.com.model.PermissionCollection;
import br.com.model.User;
import br.com.model.UserFilm;
import br.com.util.LogRegister;
import br.com.util.Message;
import br.com.util.WebService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vitor
 */
public class FilmeController extends HttpServlet {

    private static final String LIST = "/view/film/list.jsp";

    private static final String SEARCH = "/view/film/search.jsp";

    private static final String VIEW = "/view/film/view.jsp";

    private static final String MODULE = "Filme";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");
        User _user = (User) request.getSession(true).getAttribute("_user");

        String forward = null;
        CommentBO commentBO = new CommentBO();

        String action = request.getParameter("action");
        String title = request.getParameter("search");
        String id = request.getParameter("id");

        switch (action) {
            case "recentes":
                forward = SEARCH;
                request.setAttribute("filmes", WebService.recents());
                break;

            case "melhores":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.melhorClassificado()));
                break;

            case "list":
                forward = LIST;
                request.setAttribute("filmes", WebService.allFilms());
                break;

            case "search":
                forward = SEARCH;
                request.setAttribute("filmes", WebService.searchFilms(title));
                break;

            case "delete":
                if (_permissions.check(_user.getProfile(), MODULE, action)) {
                    id = request.getParameter("id");
                    String userId = request.getParameter("user");
                    String date = request.getParameter("date");
                    String time = request.getParameter("time");
                    commentBO.delete(id, userId, date, time);
                    User user = new UserBO().getUser(Integer.parseInt(userId));
                    Filme filme = WebService.getFilm(id);
                    LogRegister.singleton().toLog(MODULE, action, "Comentário de [" + user.getName() + "] em [" + filme.getNome() + "] deletado.", _user.getId());
                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

            case "view":
                forward = VIEW;
                request.setAttribute("comments", commentBO.list(id));
                request.setAttribute("filme", WebService.getFilm(id));
                break;

            case "favoritos":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.maisFavoritados()));
                break;
            case "pretendidos":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.maisPretendidos()));
                break;

            case "assistidos":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.maisAssistidos()));
                break;
                
            case "myfavoritos":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.meusFavoritos(_user.getId())));
                break;

            case "mypretendidos":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.meusFilmesPretendidos(_user.getId())));
                break;

            case "myassistidos":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.FilmesAssisti(_user.getId())));
                break;

            case "myfilmes":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.meusFilmes(_user.getId())));
                break;
        }

        request.setAttribute("message", message);
        request.setAttribute("pageContent", forward);
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");
        User _user = (User) request.getSession(true).getAttribute("_user");

        if (_user == null) {
            message.addWarning("É necessário estar logado em um usuário.");
            response.sendRedirect("AuthenticateController?action=logon");
            return;
        }

        String forward = null;
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "comment":
                    if (_permissions.check(_user.getProfile(), MODULE, action)) {
                        String id = request.getParameter("id");
                        String comment = request.getParameter("comment");

                        if (comment == null || comment.isEmpty()) {
                            message.addWarning("Nenhum comentário informado!");
                        } else {
                            CommentBO commentBO = new CommentBO();
                            commentBO.save(new Comment(id, _user, comment));
                            Filme filme = WebService.getFilm(id);
                            LogRegister.singleton().toLog(MODULE, action, "Usuário comentou [" + filme.getNome() + "].", _user.getId());
                            forward = VIEW;
                            request.setAttribute("comments", commentBO.list(id));
                            request.setAttribute("filme", WebService.getFilm(id));
                        }
                    } else {
                        message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                    }
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);
        request.setAttribute("pageContent", forward);
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }
}

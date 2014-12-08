package br.com.controller;

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
import java.util.Map;
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
                forward = LIST;
                request.setAttribute("filmes", WebService.recents());
                break;

            case "generos":

                forward = LIST;

                ArrayList<Map> generos = WebService.getAllGender();

                String genderId = request.getParameter("genderId") != null ? request.getParameter("genderId") : null;
                String genderName = null;

                if (genderId == null || genderId.isEmpty()) {

                    genderId = generos.get(9).get("id").toString();
                    genderName = generos.get(9).get("name").toString();

                } else {

                    for (Map genero : generos) {
                        String aux = genero.get("id").toString();

                        if (aux.equals(genderId)) {
                            genderName = genero.get("name").toString();
                        }
                    }

                }

                request.setAttribute("generos", generos);
                request.setAttribute("filmes", WebService.getFilmGender(genderId));
                request.setAttribute("title", genderName);

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
                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, action)) {
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
                Filme filme = WebService.getFilm(id);
                request.setAttribute("comments", commentBO.list(id));
                request.setAttribute("filme", filme);
                
                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, "check"))
                    request.setAttribute("checks", FilmesBO.verifyFilmes(_user.getId(), id));
                
                break;

            case "melhores":
                forward = LIST;
                request.setAttribute("filmes", WebService.findFilms(FilmesBO.melhorClassificado()));
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

                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, "check")) {

                    forward = LIST;
                    request.setAttribute("filmes", WebService.findFilms(FilmesBO.meusFavoritos(_user.getId())));

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [check] no modulo [" + MODULE + "].");
                }

                break;

            case "mypretendidos":

                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, "check")) {

                    forward = LIST;
                    request.setAttribute("filmes", WebService.findFilms(FilmesBO.meusFilmesPretendidos(_user.getId())));

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [check] no modulo [" + MODULE + "].");
                }

                break;

            case "myassistidos":

                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, "check")) {

                    forward = LIST;
                    request.setAttribute("filmes", WebService.findFilms(FilmesBO.FilmesAssisti(_user.getId())));

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [check] no modulo [" + MODULE + "].");
                }

                break;

            case "myfilmes":

                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, "check")) {

                    forward = LIST;
                    request.setAttribute("filmes", WebService.findFilms(FilmesBO.meusFilmes(_user.getId())));

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [check] no modulo [" + MODULE + "].");
                }

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

        CommentBO commentBO = new CommentBO();
        
        try {
            switch (action) {
                case "comment":
                    if (_permissions.check(_user.getProfile(), MODULE, action)) {
                        String id = request.getParameter("id");
                        String comment = request.getParameter("comment");

                        if (comment == null || comment.isEmpty()) {
                            message.addWarning("Nenhum comentário informado!");
                        } else {
                            
                            commentBO.save(new Comment(id, _user, comment));
                            Filme filme = WebService.getFilm(id);
                            LogRegister.singleton().toLog(MODULE, action, "Usuário comentou [" + filme.getNome() + "].", _user.getId());
                            forward = VIEW;
                            request.setAttribute("comments", commentBO.list(id));
                            request.setAttribute("filme", filme);
                            request.setAttribute("checks", FilmesBO.verifyFilmes(_user.getId(), id));
                        }
                    } else {
                        message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                    }
                    break;

                case "check":

                    if (_permissions.check(_user.getProfile(), MODULE, "check")) {

                        String imdb = request.getParameter("imdb") != null ? request.getParameter("imdb") : null;
                        
                        if (imdb == null || imdb.isEmpty()) return;
                        
                        boolean favorito = request.getParameter("favorito") != null;
                        boolean pretendido = request.getParameter("pretendido") != null;
                        boolean assistido = request.getParameter("assistido") != null;
                        String nota = request.getParameter("nota") != null ? request.getParameter("nota") : null;

                        int aux = Integer.parseInt(nota);
                        
                        UserFilm uf = new UserFilm();
                        uf.setUser_id(_user.getId());
                        uf.setImde_id(imdb);
                        uf.setFavorito(favorito);
                        uf.setPretende_ver(pretendido);
                        uf.setAssistiu(assistido);
                        
                        if (aux < 1 || aux > 10)
                            uf.setNota(null);
                        else
                            uf.setNota(aux);

                        if (FilmesBO.verifyFilmes(uf.getUser_id(), uf.getImde_id()) == null) {
                            FilmesBO.checkMyFilmes(uf);
                        } else {
                            FilmesBO.updateMyFilmes(uf);
                        }

                        message.addMessage("Classificação realizada com sucesso!");
                        
                        Filme filme = WebService.getFilm(imdb);
                        LogRegister.singleton().toLog(MODULE, action, filme.getNome()+" [" + uf + "].", _user.getId());
                        forward = VIEW;
                        request.setAttribute("comments", commentBO.list(imdb));
                        request.setAttribute("filme", filme);
                        request.setAttribute("checks", FilmesBO.verifyFilmes(_user.getId(), imdb));

                    } else {
                        message.addWarning("Você não tem permissão de acessar a ação [check] no modulo [" + MODULE + "].");
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

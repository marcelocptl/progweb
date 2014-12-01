<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!-- Input Search -->

<section>
    <form class="form-horizontal" action="FilmeController" method="get">
        <div class="form-group">
            <div class="center">
                <div class="row">
                    <div class="col-lg-2 film-search control-label">
                        <button style="color: #999999;" class="btn-link" type="submit"><i class="glyphicon glyphicon-search" style="font-size: 24px;"></i></button>
                    </div>
                    <div class="col-lg-8">
                        <input type="hidden" name="action" value="search" />
                        <input type="text" class="film-search-input form-control" id="search" placeholder="Pensando em algum filme?" name="search" />
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>